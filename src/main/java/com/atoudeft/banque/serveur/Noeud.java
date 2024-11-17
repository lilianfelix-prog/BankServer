package com.atoudeft.banque.serveur;

import java.io.Serializable;

public class Noeud implements Serializable {

    private final Object element;
    private Noeud suivant;


    // constructeur par paramètre
    public Noeud(Object element) {
        this.element = element;
    }

    // accesseurs
    public Noeud getSuivant() {
        return suivant;
    }

    public Object getElement() {
        return element;
    }

    // mutateurs
    public void setSuivant(Noeud suivant) {
        this.suivant = suivant;
    }

    public String toString() {
        return "Noeud : " + element.toString();
    }

}
