package com.atoudeft.banque.serveur;

import com.atoudeft.banque.TypeOperation;

import java.util.Date;

/** Classe enfant qui est hérité de la classe Opération
 *
 */
public class OperationFacture extends Operation{

    double montant;
    String numFacture;
    String description;

    /**Constructeur
     *
     * @param type
     * @param montant
     * @param numFacture
     * @param description
     */
    public OperationFacture(TypeOperation type, double montant, String numFacture, String description) {

        super(type);
        this.montant =montant;
        this.numFacture = numFacture;
        this.description =description;
    }


    /**
     *
     * @return un chaîne de caractère : DATE, MONTANT, NUMÉRO DE FACTURE ET DESCRIPTION
     */
    public String toString() {

        return "\n OPERATION DATE : " +date+ " TYPE : "+type + "MONTANT : "+ montant+" NUMÉRO DE FACTURE : "+ numFacture + " DESCRIPTION: "+ description;



    }
}
