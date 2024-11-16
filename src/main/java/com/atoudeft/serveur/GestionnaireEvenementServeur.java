package com.atoudeft.serveur;

import com.atoudeft.banque.Banque;
import com.atoudeft.banque.CompteBancaire;
import com.atoudeft.banque.TypeCompte;
import com.atoudeft.banque.serveur.ConnexionBanque;
import com.atoudeft.banque.serveur.ServeurBanque;
import com.atoudeft.commun.evenement.Evenement;
import com.atoudeft.commun.evenement.GestionnaireEvenement;
import com.atoudeft.commun.net.Connexion;

/**
 * Cette classe représente un gestionnaire d'événement d'un serveur. Lorsqu'un serveur reçoit un texte d'un client,
 * il crée un événement à partir du texte reçu et alerte ce gestionnaire qui réagit en gérant l'événement.
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-09-01
 */
public class GestionnaireEvenementServeur implements GestionnaireEvenement {
    private Serveur serveur;

    /**
     * Construit un gestionnaire d'événements pour un serveur.
     *
     * @param serveur Serveur Le serveur pour lequel ce gestionnaire gère des événements
     */
    public GestionnaireEvenementServeur(Serveur serveur) {
        this.serveur = serveur;
    }

    /**
     * Méthode de gestion d'événements. Cette méthode contiendra le code qui gère les réponses obtenues d'un client.
     *
     * @param evenement L'événement à gérer.
     */
    @Override
    public void traiter(Evenement evenement) {
        Object source = evenement.getSource();
        ServeurBanque serveurBanque = (ServeurBanque)serveur;
        Banque banque;
        ConnexionBanque cnx;
        String msg, typeEvenement, argument, numCompteClient, nip, strMontant, numFacture, description, numCompteFinal;
        String[] t;
        int montant;

        if (source instanceof Connexion) {
            cnx = (ConnexionBanque) source;
            System.out.println("SERVEUR: Recu : " + evenement.getType() + " " + evenement.getArgument());
            typeEvenement = evenement.getType();
            cnx.setTempsDerniereOperation(System.currentTimeMillis());
            switch (typeEvenement) {
                /******************* COMMANDES GÉNÉRALES *******************/
                case "EXIT": //Ferme la connexion avec le client qui a envoyé "EXIT":
                    cnx.envoyer("END");
                    serveurBanque.enlever(cnx);
                    cnx.close();
                    break;
                case "LIST": //Envoie la liste des numéros de comptes-clients connectés :
                    cnx.envoyer("LIST " + serveurBanque.list());
                    break;
                /******************* COMMANDES DE GESTION DE COMPTES *******************/
                case "NOUVEAU": //Crée un nouveau compte-client :
                    if (cnx.getNumeroCompteClient()!=null) {
                        cnx.envoyer("NOUVEAU NO deja connecte");
                        break;
                    }
                    argument = evenement.getArgument();
                    t = argument.split(":");
                    if (t.length<2) {
                        cnx.envoyer("NOUVEAU NO");
                    }
                    else {
                        numCompteClient = t[0];
                        nip = t[1];
                        banque = serveurBanque.getBanque();
                        if (banque.ajouter(numCompteClient,nip)) {
                            cnx.setNumeroCompteClient(numCompteClient);
                            //cnx.setNumeroCompteActuel(banque.getNumeroCompteParDefaut(numCompteClient));
                            cnx.envoyer("NOUVEAU OK " + t[0] + " cree");
                        }
                        else
                            cnx.envoyer("NOUVEAU NO "+t[0]+" existe");
                    }
                    break;
                /******************* COMMANDES DE CONNEXION *******************/
                case "CONNECT": //connexion au compte du client
                    // Récupération du numéro de compte-client et du NIP envoyés par le client
                    argument = evenement.getArgument();
                    t = argument.split(":");
                    numCompteClient = t[0];
                    nip = t[1];

                    // Vérification que le compte n'est pas déjà utilisé par une autre connexion
                    if(serveurBanque.list().contains(numCompteClient)){
                        cnx.envoyer("CONNECT NO");
                        break;
                    }
                    banque = serveurBanque.getBanque();
                    // Vérification que le compte existe et que le NIP est correct
                    if(banque.getCompteClient(numCompteClient).getNip().equals(nip)){
                        // Inscription du numéro de compte-client et du numéro de compte-chèque dans l'objet ConnexionBanque
                        cnx.setNumeroCompteClient(numCompteClient);
                        cnx.envoyer("CONNECT OK");
                    }else{
                        cnx.envoyer("CONNECT NO");
                    }
                    break;
                /******************* COMMANDES EPARGNE *******************/
                case "EPARGNE":
                    if (cnx.getNumeroCompteClient()==null) {
                        cnx.envoyer("EPARGNE NO");
                        break;
                    }
                    banque = serveurBanque.getBanque();
                    //Aller dans le array des comptes bancaire client et verifier qu'il n'y a pas de compte epargne
                    if(banque.getNumeroCompteParDefaut(cnx.getNumeroCompteClient(), TypeCompte.EPARGNE) == null){
                        cnx.envoyer("EPARGNE NO");
                    }else{

                    }

                    break;
                    
                /******************* COMMANDES DE SELECTION *******************/
                case "SELECT":
                    // vérifiez que le client est bien connecté à son compte-client
                    if (cnx.getNumeroCompteClient()==null) {
                        cnx.envoyer("SELECT NO");
                        break;
                    }
                    argument = evenement.getArgument();
                    banque = serveurBanque.getBanque();
                    if (argument.equals("cheque")){
                        cnx.setNumeroCompteActuel(banque.getNumeroCompteParDefaut(cnx.getNumeroCompteClient(), TypeCompte.CHEQUE));
                        cnx.envoyer("SELECT OK");
                        break;
                    }
                    if (argument.equals("epargne")){
                        cnx.setNumeroCompteActuel(banque.getNumeroCompteParDefaut(cnx.getNumeroCompteClient(), TypeCompte.EPARGNE));
                        cnx.envoyer("SELECT OK");
                        break;
                    } else {
                        cnx.envoyer("SELECT NO");
                    }
                    break;
                /******************* COMMANDES POUR CREDITER COMPTE *******************/
                case "DEPOT":
                    argument = evenement.getArgument();
                    montant = 0;
                    try {
                        montant = Integer.parseInt(argument);
                    }
                    catch (NumberFormatException e) {
                        cnx.envoyer("DEPOT NO NumberFormatException");
                    }
                    banque = serveurBanque.getBanque();
                    if(banque.deposer(montant, cnx.getNumeroCompteActuel())){
                        cnx.envoyer("DEPOT OK");
                    }else{
                        cnx.envoyer("DEPOT NO");
                    }
                    break;

                /******************* COMMANDES POUR DEBITER COMPTE *******************/
                case "RETRAIT":
                    argument = evenement.getArgument();
                    montant = 0;
                    try {
                        montant = Integer.parseInt(argument);
                    }
                    catch (NumberFormatException e) {
                        cnx.envoyer("RETRAIT NO NumberFormatException");
                    }
                    banque = serveurBanque.getBanque();
                    if(banque.retirer(montant, cnx.getNumeroCompteActuel())){
                        cnx.envoyer("RETRAIT OK");
                    }else{
                        cnx.envoyer("RETRAIT NO");
                    }
                    break;

                /******************* COMMANDES POUR PAYER FACTURE *******************/
                case "FACTURE":
                    argument = evenement.getArgument();
                    t = argument.split(" ");
                    strMontant = t[0];
                    numFacture = t[1];
                    description = t[2];
                    montant = 0;
                    try {
                        montant = Integer.parseInt(strMontant);
                    }
                    catch (NumberFormatException e) {
                        cnx.envoyer("FACTURE NO NumberFormatException");
                    }
                    banque = serveurBanque.getBanque();
                    if(banque.payerFacture(montant, cnx.getNumeroCompteActuel(), numFacture, description)){
                        cnx.envoyer("FACTURE OK");
                    }else{
                        cnx.envoyer("FACTURE NO");
                    }
                    break;

                /******************* COMMANDES POUR TRANSFER *******************/
                case "TRANSFER":
                    argument = evenement.getArgument();
                    t = argument.split(" ");
                    strMontant = t[0];
                    numCompteFinal = t[1];
                    montant = 0;
                    try {
                        montant = Integer.parseInt(strMontant);
                    }
                    catch (NumberFormatException e) {
                        cnx.envoyer("TRANSFER NO NumberFormatException");
                    }
                    banque = serveurBanque.getBanque();
                    if(banque.transferer(montant, cnx.getNumeroCompteActuel(), numCompteFinal)){
                        cnx.envoyer("TRANSFER OK");
                    }else{
                        cnx.envoyer("TRANSFER NO");
                    }
                    break;

                    /******************* TRAITEMENT PAR DÉFAUT *******************/
                default: //Renvoyer le texte recu convertit en majuscules :
                    msg = (evenement.getType() + " " + evenement.getArgument()).toUpperCase();
                    cnx.envoyer(msg);
            }
        }
    }
}