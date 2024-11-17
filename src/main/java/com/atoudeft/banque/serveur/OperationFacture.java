package com.atoudeft.banque.serveur;

import com.atoudeft.banque.TypeOperation;

import java.util.Date;

public class OperationFacture extends Operation{

    double montant;
    String numFacture;
    String description;

    public OperationFacture(TypeOperation type, double montant, String numFacture, String description) {

        super(type);
        this.montant =montant;
        this.numFacture = numFacture;
        this.description =description;
    }

    public String toString() {

        return "\n OPERATION DATE : " +date+ " TYPE : "+type + "MONTANT : "+ montant+" NUMÉRO DE FACTURE : "+ numFacture + " DESCRIPTION: "+ description;



    }
}
