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
public class Competences {
    /**
     * L'énumération contenant les compétences d'un formateur.
     */
    public enum Competence{
        JAVA("Java"), BD("BD");
        
        private String libelle;
        
        Competence(){
            
        }
        
        Competence(String libelle){
            this.libelle=libelle;
        }

        public String getLibelle() {
            return libelle;
        }

        public void setLibelle(String libelle) {
            this.libelle = libelle;
        }
        
    }
}
