package com.atoudeft.banque.serveur;

import com.atoudeft.banque.TypeOperation;

import java.io.Serializable;
import java.util.Date;

/** Classe abstrait mère qui hérite OperationDepot, OperationFacture, OperationRetrait et OperationTransfert
 *
 */

public abstract class Operation implements Serializable {

    Date date;
    TypeOperation type;

    /**
     *  Constructeur qui contient type et date
      */


    public Operation(TypeOperation type ) {

            this.type = type;
            this.date =new Date(System.currentTimeMillis());;

    }
}
