package com.atoudeft.banque.serveur;

import com.atoudeft.banque.TypeOperation;

import java.util.Date;

public class OperationRetrait extends Operation {

    double montant;

    public OperationRetrait(TypeOperation type, double montant) {
        super(type);
        this.montant = montant;

    }

    public String toString() {

        return " DATE : " +date+ " TYPE : "+type + "MONTANT : "+ montant;


    }
}
