package com.atoudeft.banque.serveur;

import com.atoudeft.banque.TypeOperation;

import java.util.Date;

public class OperationTransfer extends Operation {

    double montant;
    String numCompteFinal;

    public OperationTransfer(TypeOperation type,  double montant, String numCompteFinal) {

        super(type);
        this.montant = montant;
        this.numCompteFinal = numCompteFinal;
    }

    public String toString () {

        return "\n OPERATION DATE : " +date+ " TYPE : "+type + "MONTANT : "+ montant+ "NUMÉRO COMPTE DESTINATAIRE: "+ numCompteFinal ;


    }
}
