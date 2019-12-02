/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.entities;

import fr.miage.tlse.enumerations.Competences;
import fr.miage.tlse.enumerations.Competences.Competence;
import fr.miage.tlse.enumerations.Equipements;
import fr.miage.tlse.enumerations.Equipements.Equipement;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Sylvia
 */
@Entity
public class Formation implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * La référence de la formation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codeFormation;

    /**
     * L'intitulé de la formation.
     */
    private String intitule;
    
    /**
     * La description de la formation.
     */
    private String description;
    
    /**
     * L'énumération contenant les niveaux possibles de la formation.
     */
    public enum Niveau{
        EXPERT,INGENIEUR,TECHNICIEN
    }
    private Niveau niveau;
    
    /**
     * La durée de la formation.
     */
    private int duree;
    
    /**
     * La capacité minimale que peut accueillir la formation.
     */
    private int capaciteMin;
    
    /**
     * La capacité maximale que peut accueillir la formation.
     */
    private int capaciteMax;
    
    /**
     * Le tarif de la formation.
     */
    private int tarif;
    
    
    
    /**
     * Liste des équipements nécessaires dans la salle pour la formation.
     */
    private List<Equipements> equipementsNecessaires;
    
    
    
    /**
     * Liste des compétences nécessaires d'un formateur pour la formation.
     */
    private List<Competences> competencesNecessaires;
    
    public Formation(){
        
    }
    
    public Formation(long codeFormation, String intitule, String description, Niveau niveau, int duree, int capaciteMin, int capaciteMax, int tarif, List<Equipements> equipementsNecessaires, List<Competences> competencesNecessaires){
        
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setCapaciteMin(int capaciteMin) {
        this.capaciteMin = capaciteMin;
    }

    public void setCapaciteMax(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    

    public void setEquipementsNecessaires(List<Equipements> equipementsNecessaires) {
        this.equipementsNecessaires = equipementsNecessaires;
    }

    

    public void setCompetencesNecessaires(List<Competences> competencesNecessaires) {
        this.competencesNecessaires = competencesNecessaires;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getDescription() {
        return description;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public int getDuree() {
        return duree;
    }

    public int getCapaciteMin() {
        return capaciteMin;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public int getTarif() {
        return tarif;
    }

    public List<Equipements> getEquipementsNecessaires() {
        return equipementsNecessaires;
    }

    public List<Competences> getCompetencesNecessaires() {
        return competencesNecessaires;
    }
            
    public Long getCode() {
        return codeFormation;
    }

    public void setCode(Long code) {
        this.codeFormation = code;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeFormation != null ? codeFormation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formation)) {
            return false;
        }
        Formation other = (Formation) object;
        if ((this.codeFormation == null && other.codeFormation != null) || (this.codeFormation != null && !this.codeFormation.equals(other.codeFormation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "appCommerciale.business.Formation[ codeFormation=" + codeFormation + " ]";
    }
    
}
