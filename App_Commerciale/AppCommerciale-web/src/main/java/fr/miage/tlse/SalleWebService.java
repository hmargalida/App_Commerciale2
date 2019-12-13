/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse;

import com.google.gson.Gson;
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
@Path("/salle")
@RequestScoped
public class SalleWebService {

    

    @Context
    private UriInfo context;
    
    private Gson gson = new Gson();

    /**
     * Creates a new instance of FormationWebService
     */
    public SalleWebService() {
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

    
    
    
}
