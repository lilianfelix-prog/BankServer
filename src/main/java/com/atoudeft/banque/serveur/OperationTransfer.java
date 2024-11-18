package com.atoudeft.banque.serveur;

import com.atoudeft.banque.TypeOperation;

import java.util.Date;

/** classe enfant qui est hérité de Opération
 *
 */
public class OperationTransfer extends Operation {

    double montant;
    String numCompteFinal;

    /** Constructeur
     *
     * @param type
     * @param montant
     * @param numCompteFinal
     */
    public OperationTransfer(TypeOperation type,  double montant, String numCompteFinal) {

        super(type);
        this.montant = montant;
        this.numCompteFinal = numCompteFinal;
    }

    /** Cette méthode retourne un chaîne de
     *
     * @return une chaine de caractère : DATE, MONTANT et NUMÉROCOMPTEDESTINATAIRE
     */
    public String toString () {

        return "\n OPERATION DATE : " +date+ " TYPE : "+type + "MONTANT : "+ montant+ "NUMÉRO COMPTE DESTINATAIRE: "+ numCompteFinal ;


    }
}
