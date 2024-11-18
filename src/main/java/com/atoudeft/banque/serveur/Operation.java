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
     *  Constructeur qui prend en parametre le type d'operation
     *  et initialise la date en utilisant l'heure du systeme
     *
     **/

    public Operation(TypeOperation type ) {

            this.type = type;
            this.date =new Date(System.currentTimeMillis());;

    }
}
