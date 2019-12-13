/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.business;

import fr.miage.tlse.entities.Formation;
import fr.miage.tlse.enumerations.Competences;
import fr.miage.tlse.enumerations.Competences.Competence;
import fr.miage.tlse.enumerations.Equipements;
import fr.miage.tlse.enumerations.Equipements.Equipement;
import fr.miage.tlse.exceptions.FormationNotFoundException;
import fr.miage.tlse.export.CatalogueExport;
import fr.miage.tlse.jms.SendCatalogue;
import fr.miage.tlse.repositories.FormationFacadeLocal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Sylvia
 */
@Stateless
public class GestionCommerciale implements GestionCommercialeLocal {

    @EJB
    private FormationFacadeLocal formationFacade;
    
    private SendCatalogue catalogue;
    
    private CatalogueExport catalogueExport;
    
    public GestionCommerciale(){
        this.catalogueExport = new CatalogueExport();
    }
    /**
     * Fonction qui vérifie si une formation existe
     * @param codeFormation
     * @return
     * @throws FormationNotFoundException 
     */
    @Override
    public boolean verifierFormation(long codeFormation) throws FormationNotFoundException {
        //Récupérer demandes de formation par JMS
        final Formation f = this.formationFacade.find(codeFormation);
        if (f == null) 
        {
            throw new FormationNotFoundException("Erreur : Formation inexistante.");
        } else 
        {
            return true;
        }
    }

    /**
     * Fonction qui permet de supprimer une formation 
     * @param codeFormation
     * @throws FormationNotFoundException 
     */
    @Override
    public void supprimerFormation(long codeFormation) throws FormationNotFoundException {
        final Formation f = this.formationFacade.find(codeFormation);
        if (f == null) {
            throw new FormationNotFoundException("Erreur : Formation inexistante.");
        }
        this.formationFacade.remove(f);
        this.catalogueExport.setListeFormation(afficherToutesFormations());
        this.catalogue.sendCatalogue(catalogueExport);
    }

    /**
     * Fonction qui permet de modifier des attributs d'une formation existante
     * @param codeFormation
     * @param intitule
     * @param description
     * @param niveau
     * @param duree
     * @param capaciteMin
     * @param capaciteMax
     * @param tarif
     * @param equipementsNecessaires
     * @param competencesNecessaires
     * @throws FormationNotFoundException 
     */
    @Override
    public void modifierFormation(long codeFormation, String intitule, String description, Formation.Niveau niveau, int duree, int capaciteMin, int capaciteMax, int tarif, List<Equipements> equipementsNecessaires, List<Competences> competencesNecessaires) throws FormationNotFoundException {
        final Formation f = this.formationFacade.find(codeFormation);
        if (f == null) {
            throw new FormationNotFoundException("Erreur : Formation inexistante.");
        }
        f.setIntitule(intitule);
        f.setDescription(description);
        f.setNiveau(niveau);
        f.setCapaciteMin(capaciteMin);
        f.setCapaciteMax(capaciteMax);
        f.setDuree(duree);
        f.setTarif(tarif);
        f.setEquipementsNecessaires(equipementsNecessaires);
        f.setCompetencesNecessaires(competencesNecessaires);
        this.formationFacade.edit(f);
        this.catalogueExport.setListeFormation(afficherToutesFormations());
        this.catalogue.sendCatalogue(catalogueExport);
    }

    /**
     * Fonction qui permet de créer une formation qui n'existe pas déjà
     * @param codeFormation
     * @param intitule
     * @param description
     * @param niveau
     * @param duree
     * @param capaciteMin
     * @param capaciteMax
     * @param tarif
     * @param equipements
     * @param competences
     * @return 
     */
    @Override
    public long creerFormationSiInexistante(long codeFormation, String intitule, String description, Formation.Niveau niveau, int duree, int capaciteMin, int capaciteMax, int tarif, List<Equipements> equipements, List<Competences> competences) {
        final Formation f = this.formationFacade.find(codeFormation);
        if (f == null) {
            final Formation nouvelleFormation = new Formation(codeFormation, intitule, description, niveau, duree, capaciteMin, capaciteMax, tarif, equipements, competences);
            this.formationFacade.edit(nouvelleFormation);
            this.catalogueExport.setListeFormation(afficherToutesFormations());
            this.catalogue.sendCatalogue(catalogueExport);
            return codeFormation;
        } else {
            System.out.println("Cette formation existe déjà.");
            return f.getCode();
        }
    }

    /**
     * Envoi d'un compte rendu au client
     * @param idClient
     * @return 
     */
    @Override
    public String envoiCompteRendu(long idClient) {
        //Recevoir informations : date Formation/Session, nom salle, effectif retenu, montant à payer
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateDuJour = new SimpleDateFormat("dd-MM-yyyy");
        //Vérifier si à J+30 la formation a été validée
        //if(){
        //return "Nous ne pouvons pas donner suite à votre demande de formation";
        //}
        //else{
            return "Compte rendu positif pour la demande de formation";
        //}
        //envoie du CR par JMS à l'appli cliente
    }

    /**
     * Formation qui permet d'afficher une formation
     * @param codeFormation
     * @return
     * @throws FormationNotFoundException 
     */
    @Override
    public Long afficherFormation(long codeFormation) throws FormationNotFoundException {
        final Formation f = this.formationFacade.find(codeFormation);
        if (f == null) {
            throw new FormationNotFoundException("Erreur : Formation inexistante.");
        } else {
            return f.getCode();
        }
    }

    /**
     * Fonction qui affiche la liste des toutes les formations existantes
     * @return 
     */
    @Override
    public List<Formation> afficherToutesFormations() {
        final List<Formation> f = this.formationFacade.findAll();
        return f;
    }
}
