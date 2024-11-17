package com.atoudeft.banque.serveur;

import com.atoudeft.banque.TypeOperation;

import java.io.Serializable;
import java.util.Date;



public abstract class Operation implements Serializable {

    Date date;
    TypeOperation type;


    public Operation(TypeOperation type ) {

            this.type = type;
            this.date =new Date(System.currentTimeMillis());;

    }
}
