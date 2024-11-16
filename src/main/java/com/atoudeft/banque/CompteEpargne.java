package com.atoudeft.banque;

public class CompteEpargne extends CompteBancaire {

    final int LIMITE = 1000;
    float tauxInteret;
    final int FRAIS  = 2;
    /**
     * Crée un compte bancaire.
     *
     * @param numero numéro du compte
     * @param type   type du compte
     */
    public CompteEpargne(String numero, TypeCompte type,  float tauxInteret) {

        super(numero, type);
        this.tauxInteret = tauxInteret;
    }

    /**
     *  Cette méthode permet ajouter un montant au compte
     * @param montant
     * @return true s'il le montant est strictement positif, sinon return false
     */
    @Override
    public boolean crediter(double montant) {

        if (montant > 0) {

            setSolde(getSolde() + montant);
            return true;
        }

        return false;
    }

    /**
     * Cette méthode permet de retirer les fonds s'il le solde et le montant est strictment positif
     *  Et s'il le solde est plus bas que 1000, des frais de 2$ va être chargé retrait
     *
     *  * @param montant
     * @return true s'il le montant et le solde  est strictment positif
     */
    @Override
    public boolean debiter(double montant) {

        if (montant > 0 && getSolde() >= montant) {
            if (getSolde() < LIMITE) {

                montant = montant + FRAIS;
            }

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

    /**
     *  Cette méthode permet de calculer le taux d'intérêt du Solde et de l'additionner
     * @param tauxInteret  Taux d'Intérêt
     *
     */
    public void ajouterInterets(double tauxInteret) {

         setSolde(getSolde()+((getSolde() * tauxInteret)));

    }

}
