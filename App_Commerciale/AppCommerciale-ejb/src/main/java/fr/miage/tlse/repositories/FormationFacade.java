/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.repositories;

import fr.miage.tlse.entities.Formation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sylvia
 */
@Stateless
public class FormationFacade extends AbstractFacade<Formation> implements FormationFacadeLocal {

    @PersistenceContext(unitName = "CommercialePersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormationFacade() {
        super(Formation.class);
    }

    
}
