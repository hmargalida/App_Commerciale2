/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.export;

import fr.miage.tlse.entities.Formation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sylvia
 */
public class CatalogueExport {
    private List<Formation> listeFormation;
    
    public CatalogueExport(){
        this.listeFormation = new ArrayList<Formation>();
    }
    
    public CatalogueExport(List<Formation> liste){
        this.listeFormation=liste;
    }

    public List<Formation> getListeFormation() {
        return listeFormation;
    }

    public void setListeFormation(List<Formation> listeFormation) {
        this.listeFormation = listeFormation;
    }
    
}
