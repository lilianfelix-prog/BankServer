package com.atoudeft.banque.serveur;

import com.atoudeft.banque.TypeOperation;

import java.util.Date;

public class OperationFacture extends Operation{

    String montant;
    String numFacture;
    String description;

    public OperationFacture(TypeOperation type, Date date, String montant, String numFacture, String description) {

        super(type,date);
        this.montant =montant;
        this.numFacture = numFacture;
        this.description =description;
    }

    public String toString() {

        return " DATE : " +date+ " TYPE : "+type + "MONTANT : "+ montant+" NUMÉRO DE FACTURE : "+ numFacture + " DESCRIPTION: "+ description;



    }
}
