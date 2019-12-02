/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.business;

import fr.miage.tlse.entities.Formation;
import fr.miage.tlse.enumerations.Competences.Competence;
import fr.miage.tlse.enumerations.Equipements.Equipement;
import fr.miage.tlse.entities.Formation.Niveau;
import fr.miage.tlse.enumerations.Competences;
import fr.miage.tlse.enumerations.Equipements;
import fr.miage.tlse.exceptions.FormationNotFoundException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Sylvia
 */
@Local
public interface GestionCommercialeLocal {
    boolean verifierFormation(long codeFormation) throws FormationNotFoundException;
    
    void supprimerFormation(long codeFormation) throws FormationNotFoundException;
    
    void modifierFormation(long codeFormation, String intitule, String description, Formation.Niveau niveau, int duree, int capaciteMin, int capaciteMax, int tarif, List<Equipements> equipements, List<Competences> competences) throws FormationNotFoundException;
    
    long creerFormationSiInexistante(long codeFormation, String intitule, String description, Niveau niveau, int duree, int capaciteMin, int capaciteMax, int tarif, List<Equipements> equipements, List<Competences> competences);
    
    String envoiCompteRendu(long idClient);
    
    Long afficherFormation(long codeFormation) throws FormationNotFoundException;
    
    List<Formation> afficherToutesFormations();
}
