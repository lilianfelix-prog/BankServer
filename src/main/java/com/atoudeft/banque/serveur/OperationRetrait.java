package com.atoudeft.banque.serveur;

import com.atoudeft.banque.TypeOperation;

import java.util.Date;

public class OperationRetrait extends Operation {

    double montant;


    /**Constructeur
     *
     * @param type
     * @param montant
     */
    public OperationRetrait(TypeOperation type, double montant) {
        super(type);
        this.montant = montant;

    }

    /** Cette méthode retourne un transaction d'un retrait qui inclut la date, le type d'opération et le montant
     *
     * @return un chaine de caractère: DATE, TYPE et MONTANT
     */
    public String toString() {

        return "\n OPERATION DATE : " +date+ " TYPE : "+type + "MONTANT : "+ montant;


    }
}
