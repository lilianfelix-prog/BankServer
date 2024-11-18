package com.atoudeft.banque;

import com.atoudeft.banque.serveur.CompteCheque;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Banque implements Serializable {
    private String nom;
    private List<CompteClient> comptes;


    public Banque(String nom) {
        this.nom = nom;
        this.comptes = new ArrayList<>();
    }

    /**
     * Recherche un compte-client à partir de son numéro.
     *
     * @param numeroCompteClient le numéro du compte-client
     * @return le compte-client s'il a été trouvé. Sinon, retourne null
     */
    public CompteClient getCompteClient(String numeroCompteClient) {
        // parcours la list des comptes clients et retourne celui qui correspond
        for (CompteClient client : comptes) {
                if (client.getNumero().equals(numeroCompteClient)) {
                    return client;
                }
            }

        return null;
        //erreur avec le code original, probleme non resolu
        /*
        CompteClient cpt = new CompteClient(numeroCompteClient,"");
        int index = this.comptes.indexOf(cpt);
        if (index != -1)
            return this.comptes.get(index);
        else
            return null;
        */
    }

    /**
     * Vérifier qu'un compte-bancaire appartient bien au compte-client.
     *
     * @param numeroCompteBancaire numéro du compte-bancaire
     * @param numeroCompteClient    numéro du compte-client
     * @return  true si le compte-bancaire appartient au compte-client
     */
    public boolean appartientA(String numeroCompteBancaire, String numeroCompteClient) {
        for (CompteBancaire cpb : getCompteClient(numeroCompteClient).getComptes()) {
            if (cpb.getNumero().equals(numeroCompteBancaire)) {
                return false;
            }
        }
        return false;
    }

    /**
     * Effectue un dépot d'argent dans un compte-bancaire
     *
     * @param montant montant à déposer
     * @param numeroCompte numéro du compte
     * @return true si le dépot s'est effectué correctement
     */
    public boolean deposer(double montant, String numeroCompte) {
        // parcourir les comptes banquaires dans chaque compte client pour determiner quel compte crediter
        for(CompteClient cpt: comptes){
            for(CompteBancaire cpb: cpt.getComptes()){
                if(cpb.getNumero().equals(numeroCompte)){
                    cpb.crediter(montant);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Effectue un retrait d'argent d'un compte-bancaire
     *
     * @param montant montant retiré
     * @param numeroCompte numéro du compte
     * @return true si le retrait s'est effectué correctement
     */
    public boolean retirer(double montant, String numeroCompte) {
        // parcourir les comptes banquaires dans chaque compte client pour determiner quel compte debiter
        for(CompteClient cpt: comptes){
            for(CompteBancaire cpb: cpt.getComptes()){
                if(cpb.getNumero().equals(numeroCompte)){
                    cpb.debiter(montant);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Effectue un transfert d'argent d'un compte à un autre de la même banque
     * @param montant montant à transférer
     * @param numeroCompteInitial   numéro du compte d'où sera prélevé l'argent
     * @param numeroCompteFinal numéro du compte où sera déposé l'argent
     * @return true si l'opération s'est déroulée correctement
     */
    public boolean transferer(double montant, String numeroCompteInitial, String numeroCompteFinal) {
        //verifier que le numero de compte destinataire et initial exist
        int compteur = 0;
        for(CompteClient cpt: comptes) {
            if (appartientA(numeroCompteInitial, cpt.getNumero()) || appartientA(numeroCompteFinal, cpt.getNumero())) {
                compteur += 1;
            }
        }
        if(compteur == 2){
            //si les deux exist aller chercher le compte bancaire initial et faire le transfaire
            for(CompteClient cpt: comptes) {
                for (CompteBancaire cpb : cpt.getComptes()) {
                    if (cpb.getNumero().equals(numeroCompteInitial)) {
                        cpb.transferer(montant, numeroCompteFinal);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Effectue un paiement de facture.
     * @param montant montant de la facture
     * @param numeroCompte numéro du compte bancaire d'où va se faire le paiement
     * @param numeroFacture numéro de la facture
     * @param description texte descriptif de la facture
     * @return true si le paiement s'est bien effectuée
     */
    public boolean payerFacture(double montant, String numeroCompte, String numeroFacture, String description) {
        // parcourir les comptes banquaires dans chaque compte client pour determiner quel compte debiter la facture
        for(CompteClient cpt: comptes){
            for(CompteBancaire cpb: cpt.getComptes()){
                if(cpb.getNumero().equals(numeroCompte)){
                    cpb.payerFacture(numeroFacture, montant, description);
                    cpb.debiter(montant);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Crée un nouveau compte-client avec un numéro et un nip et l'ajoute à la liste des comptes.
     *
     * @param numCompteClient numéro du compte-client à créer
     * @param nip nip du compte-client à créer
     * @return true si le compte a été créé correctement
     */
    public boolean ajouter(String numCompteClient, String nip) {
        // Vérifie si le numéro de compte-client contient entre 6 et 8 caractères et seulement des lettres majuscules et des chiffres
      if (numCompteClient.length() <6 || numCompteClient.length() >8  )  {
          return false;
      }

      if (!numCompteClient.equals(numCompteClient.toUpperCase())) {
          return false;
      }
      // Vérifie s'il existe déjà un compte-client avec le même numéro
      for (CompteClient cpt: comptes ) { // pacourir s'il existe un compte bancaire

          if (cpt.getNumero().equals(numCompteClient) ) {
              return false;
          }
      }
      // Vérifie si le nip contient entre 4 et 5 caractères
      if (nip.length() < 4 || nip.length() > 5) {
          return false;
          }
        // Vérifie que chaque caractère du nip est un chiffre en utilisant une boucle for
      for ( int i = 0; i< nip.length(); i++) {
          // Caractère n'est pas un chiffre
            if ( nip.charAt(i) < '0' || nip.charAt(i) > '9') {
                return false;
            }
      }
        // Crée un nouveau CompteClient avec le numéro de compte-client et le nip fournis
        CompteClient client = new CompteClient(numCompteClient, nip);
        // Génère un nouveau numéro de compte pour le CompteCheque et le mettre dans un compte cheque
        CompteCheque cheque = new CompteCheque(CompteBancaire.genereNouveauNumero(), TypeCompte.CHEQUE);

        // Ajoute le CompteCheque au CompteClient
        client.ajouter(cheque);
        // Ajoute le CompteClient à la liste des comptes de la banque

        this.comptes.add(client);
        return true;
    }

    /**
     * Retourne le numéro du compte-chèque d'un client à partir de son numéro de compte-client.
     *
     * @param numCompteClient numéro de compte-client
     * @return numéro du compte-chèque du client ayant le numéro de compte-client
     */
    public String getNumeroCompteParDefaut(String numCompteClient, TypeCompte type) {
        // Parcourt la liste des comptes pour trouver le compte-client correspondant
        for (CompteClient cpt: comptes ) {
            if (cpt.getNumero().equals(numCompteClient)) {
                for (CompteBancaire cpb: cpt.getComptes()){
                    if(cpb.getType().equals(type)){
                        return cpb.getNumero();
                    }
                }
            }
        }
         return null;

    }
}