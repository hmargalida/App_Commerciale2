/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.repositories;

import fr.miage.tlse.entities.Formation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Sylvia
 */
@Local
public interface FormationFacadeLocal {

    void create(Formation formation);

    void edit(Formation formation);

    void remove(Formation formation);

    Formation find(Object id);

    List<Formation> findAll();

    List<Formation> findRange(int[] range);

    int count();
    
}
