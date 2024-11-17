package com.atoudeft.banque.serveur;

import com.atoudeft.banque.TypeOperation;

public class OperationDepot extends Operation {

    double montant;

    public OperationDepot(TypeOperation type, double montant) {

        super( type );
        this.montant = montant;

    }

    public String toString(){

        return  " DATE : " +date+ " TYPE : "+type + "MONTANT : "+ montant ;
    }

}
