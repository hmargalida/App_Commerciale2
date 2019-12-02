/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse;

import com.google.gson.Gson;
import fr.miage.tlse.business.GestionCommercialeLocal;
import fr.miage.tlse.entities.Formation;
import fr.miage.tlse.enumerations.Competences;
import fr.miage.tlse.enumerations.Equipements;
import fr.miage.tlse.exceptions.FormationNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Sylvia
 */
@Path("/formation")
@RequestScoped
public class FormationWebService {

    GestionCommercialeLocal gestionCommerciale = lookupGestionCommercialeLocal();

    fr.miage.tlse.repositories.FormationFacadeLocal formationFacade = lookupFormationFacadeLocal();

    @Context
    private UriInfo context;
    
    private Gson gson = new Gson();

    /**
     * Creates a new instance of FormationWebService
     */
    public FormationWebService() {
    }

    /**
     * Retrieves representation of an instance of fr.miage.tlse.FormationWebService
     * @return an instance of java.lang.String
     */
    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }*/

    /**
     * PUT method for updating or creating an instance of FormationWebService
     * @param content representation for the resource
     */
    /*@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }*/

    private fr.miage.tlse.repositories.FormationFacadeLocal lookupFormationFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (fr.miage.tlse.repositories.FormationFacadeLocal) c.lookup("java:global/AppCommerciale-ear/AppCommerciale-ejb-1.0/FormationFacade!fr.miage.tlse.repositories.FormationFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private GestionCommercialeLocal lookupGestionCommercialeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (GestionCommercialeLocal) c.lookup("java:global/AppCommerciale-ear/AppCommerciale-ejb-1.0/GestionCommerciale!fr.miage.tlse.business.GestionCommercialeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    /**
     * Fonction qui récupère les informations d'une formation en donnant son code
     * @param idFormation
     * @return
     * @throws FormationNotFoundException 
     */
    @Path("/formation")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Long getFormation(@QueryParam("Formation") long idFormation) throws FormationNotFoundException {
        return this.gestionCommerciale.afficherFormation(idFormation);
    }
    
    @Path("/formation")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public long creerFormation(@QueryParam("Formation") long idFormation, @QueryParam("Intitule") String intitule, @QueryParam("Description") String description, @QueryParam("Niveau") Formation.Niveau niveau, @QueryParam("Duree") int duree, @QueryParam("CapaciteMin") int capaciteMin, @QueryParam("CapaciteMax") int capaciteMax, @QueryParam("Tarif") int tarif) {
        return this.gestionCommerciale.creerFormationSiInexistante(idFormation, intitule, description, niveau, duree, capaciteMin, capaciteMax, tarif, null, null);
    }
    /*
    @Path("/formation")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void modifierFormation(@QueryParam("Formation") long idFormation, @QueryParam("Intitule") String intitule, @QueryParam("Description") String description, @QueryParam("Niveau") Formation.Niveau niveau, @QueryParam("Duree") int duree, @QueryParam("CapaciteMin") int capaciteMin, @QueryParam("CapaciteMax") int capaciteMax, @QueryParam("Tarif") int tarif, @QueryParam("Equipements") List<Equipements> equipementsNecessaires, @QueryParam("Competences") List<Competences> competencesNecessaires) throws FormationNotFoundException{
        this.gestionCommerciale.modifierFormation(idFormation, intitule, description, niveau, duree, capaciteMin, capaciteMax, tarif, equipementsNecessaires, competencesNecessaires);
    }*/
    
    @Path("/formation")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void supprimerFormation(@QueryParam("Formation") long idFormation) throws FormationNotFoundException{
        this.gestionCommerciale.supprimerFormation(idFormation);
    }
    
    @Path("/formations")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String afficherToutesFormations(){
        List<Formation> formations = this.gestionCommerciale.afficherToutesFormations();
        String retour = gson.toJson(formations);
        return retour;
    }
}
