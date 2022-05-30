/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae_tp_etu;

/**
 *
 * @author educe
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eric
 */
public enum TypeGraphe {

    CHAINE, CYCLE, PERSO, RANDOM, GRILLE, TORE, ANC;

    @Override
    public String toString() {
        switch (this) {
            case PERSO:
                return "perso";
            case CHAINE:
                return "chaine";
            case CYCLE:
                return "cycle";
            case RANDOM:
                return "graphe aleatoire";
            case GRILLE:
                return "grille carrée";
            case TORE:
                return "tore";
            case ANC:
                return "arbre n-aire complet";
        }
        return "";
    }

    
}

