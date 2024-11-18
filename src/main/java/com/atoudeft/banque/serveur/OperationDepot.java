package com.atoudeft.banque.serveur;

import com.atoudeft.banque.TypeOperation;

import java.util.Date;


/** Classe enfant qui hérite de classe Operation
 *
 */
public class OperationDepot extends Operation {

    double montant;

    /**Constructeur
     *
     * @param type
     * @param montant
     */
    public OperationDepot(TypeOperation type, double montant) {

        super( type );
        this.montant = montant;

    }

    /**
     *
     * @return une chaîne de caractère: DATE, TYPE et MONTANT
     */
    public String toString(){

        return  "\n OPERATION DATE : " +date+ " TYPE : "+type + "MONTANT : "+ montant ;
    }

}
