package com.atoudeft.banque.serveur;


import com.atoudeft.banque.CompteBancaire;
import com.atoudeft.banque.TypeCompte;

public class CompteCheque extends CompteBancaire {

    /**
     * Crée un compte bancaire.
     *
     * @param numero numéro du compte
     * @param type   type du compte
     */
    public CompteCheque(String numero, TypeCompte type) {
        super(numero, type);
    }

    /**
     * Cette méthode permet d'ajouter le montant du solde s'il est strictement postif
     * @param montant
     * @return true, s'il le montant est strictement positif, sinon retourne false
     */
    @Override
    public boolean crediter(double montant) {
        if ( montant > 0) {
            setSolde(getSolde() + montant); // appeler le solde de l'utilisateur pour ensuite additionner
            return true;
        }else { return false;}

    }

    /**
     *  La méthode débiter permet de retirer le montant du solde s'Ilest strictement
     *  positif et qu'il y a assez de fonds.
     * @param montant
     * @return boolean true, si le montant est strictement positif, sinon false
     */
    @Override
    public boolean debiter(double montant) {
        if (montant > 0 && getSolde() >= montant) {
            setSolde(getSolde() - montant);
            return true;
        }
        return false;
    }

    @Override
    public boolean payerFacture(String numeroFacture, double montant, String description) {
        return false;
    }

    @Override
    public boolean transferer(double montant, String numeroCompteDestinataire) {
        return false;
    }
}
