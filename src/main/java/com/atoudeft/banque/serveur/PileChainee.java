package com.atoudeft.banque.serveur;

import java.io.Serializable;

public class PileChainee implements Serializable {

    /** Credit : Simon Pichette
     *  Cette classe implémente une liste chaînée
     *
     *  Le type de l'élément est abstrait en utilisant la classe Objet
     *
     *  Cette classe dépend de la classe Noeud
     */
    private Noeud tete = null;
        private int nbElement = 0;

        // Constructeur par défaut
        public PileChainee() {

        }

        // accesseurs
        public boolean estVide()    { return nbElement == 0; }
        public int getTaille()      { return nbElement; }

        /**
         * Ajoute un élément à la tête de la liste.
         * @param obj l'élément à ajouter.
         */
        public void ajouterDebut(Object obj ) {
            // encapsuler l'objet dans un nouveau noeud
            Noeud nouveau = new Noeud(obj);

            // gérer le cas où la liste est vide
            if (tete == null) {
                tete = nouveau;
            } else {
                // tous les autres cas
                nouveau.setSuivant(tete);
                tete = nouveau;
            }
            // mettre à jour la taille
            nbElement++;
        }

        /**
         * Retirer un élément au début de la liste et le retourner.
         * @return L'objet retiré, null si la liste est vide.
         */
        public Object retirerDebut() {
            // gérer le cas où la liste est vide
            if (nbElement == 0) {
                return null;
            }

            Object contenu = tete.getElement();
            nbElement--;

            // gérer le cas o il y avait un seul élément
            if (nbElement == 0) {
                tete = null;
            } else {
                tete = tete.getSuivant();
            }
            return contenu;
        }

    /** Cette méthode  retourne une chaîne de caractère représentant les éléments d'une liste chaînée avec leurs indices
     *
     * @return
     */
    public String toString() {
            if (nbElement == 0) {
                return "Liste vide\n";
            }
            StringBuilder chaineListe = new StringBuilder();

            Noeud courant = tete;
            int position = 0;
            while (courant.getSuivant() != null) {
                chaineListe.append(position + " : " + courant.toString() + "\n");
                courant = courant.getSuivant();
                position++;
            }
            chaineListe.append(position + " : " + courant.toString() + "\n");

            return chaineListe.toString();
        }

}
