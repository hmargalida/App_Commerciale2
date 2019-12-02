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

    @Override
    public boolean verifierFormation(long codeFormation) throws FormationNotFoundException {
        //Récupérer demandes de formation par JMS
        final Formation f = this.formationFacade.find(codeFormation);
        if (f == null) //envoi JMS demande validée
        {
            throw new FormationNotFoundException("Erreur : Formation inexistante.");
        } else //envoi JMS demande refusée
        {
            return true;
        }
    }

    @Override
    public void supprimerFormation(long codeFormation) throws FormationNotFoundException {
        final Formation f = this.formationFacade.find(codeFormation);
        if (f == null) {
            throw new FormationNotFoundException("Erreur : Formation inexistante.");
        }
        this.formationFacade.remove(f);
        //Envoi du catalogue

    }

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
        //Envoi du catalogue
    }

    @Override
    public long creerFormationSiInexistante(long codeFormation, String intitule, String description, Formation.Niveau niveau, int duree, int capaciteMin, int capaciteMax, int tarif, List<Equipements> equipements, List<Competences> competences) {
        final Formation f = this.formationFacade.find(codeFormation);
        if (f == null) {
            final Formation nouvelleFormation = new Formation(codeFormation, intitule, description, niveau, duree, capaciteMin, capaciteMax, tarif, equipements, competences);
            this.formationFacade.edit(nouvelleFormation);
            //Envoi catalogue
            return codeFormation;
        } else {
            //Renvoyer erreur
            return f.getCode();
        }
    }

    @Override
    public String envoiCompteRendu(long idClient) {
        //Recevoir informations : date Formation/Session, nom salle, effectif retenu, montant à payer
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateDuJour = new SimpleDateFormat("dd-MM-yyyy");
        //if(){
        return "Nous ne pouvons pas donner suite à votre demande de formation";
        //}
        //else{

        //return "Compte rendu positif pour la demande de formation du ";
        //}
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Long afficherFormation(long codeFormation) throws FormationNotFoundException {
        final Formation f = this.formationFacade.find(codeFormation);
        if (f == null) {
            throw new FormationNotFoundException("Erreur : Formation inexistante.");
        } else {
            return f.getCode();
        }
    }

    @Override
    public List<Formation> afficherToutesFormations() {
        final List<Formation> f = this.formationFacade.findAll();
        return f;
    }
}
