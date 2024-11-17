package com.atoudeft.banque.serveur;

import com.atoudeft.banque.TypeOperation;

import java.util.Date;

abstract class OperationTransfer extends Operation {

    String montant;
    String numCompteFinal;

    public OperationTransfer(TypeOperation type, Date date, String montant, String numCompteFinal) {

        super(type, date);
        this.montant = montant;
        this.numCompteFinal = numCompteFinal;
    }

    public String toString () {

        return " DATE : " +date+ " TYPE : "+type + "MONTANT : "+ montant+ "NUMÉRO COMPTE DESTINATAIRE: "+ numCompteFinal ;


    }
}
