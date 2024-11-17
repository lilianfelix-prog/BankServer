package com.atoudeft.banque.serveur;

import com.atoudeft.banque.TypeOperation;

import java.util.Date;

public class OperationDepot extends Operation {

    String montant;

    public OperationDepot(TypeOperation type, Date date, String montant) {

        super( type, date );
        this.montant = montant;

    }

    public String toString(){

        return  " DATE : " +date+ " TYPE : "+type + "MONTANT : "+ montant ;
    }

}
