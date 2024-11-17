package com.atoudeft.banque.serveur;

import com.atoudeft.banque.TypeOperation;

import java.util.Date;

public class OperationDepot extends Operation {

    double montant;

    public OperationDepot(TypeOperation type, double montant) {

        super( type );
        this.montant = montant;

    }

    public String toString(){

        return  "\n OPERATION DATE : " +date+ " TYPE : "+type + "MONTANT : "+ montant ;
    }

}
