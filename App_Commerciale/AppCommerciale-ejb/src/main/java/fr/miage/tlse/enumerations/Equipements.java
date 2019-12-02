/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.enumerations;

/**
 *
 * @author Sylvia
 */
public class Equipements {
    /**
     * L'énumération contenant les équipements d'une salle.
     */
    public enum Equipement{
        VIDEOPROJECTEUR, TABLES, CHAISES, TABLEAU;
        
        private String libelle;
        
        Equipement(){
            
        }

        public String getLibelle() {
            return libelle;
        }

        public void setLibelle(String libelle) {
            this.libelle = libelle;
        }
        
    }
}
