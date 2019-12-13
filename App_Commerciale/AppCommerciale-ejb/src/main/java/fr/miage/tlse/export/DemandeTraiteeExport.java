/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.export;

/**
 *
 * @author Sylvia
 */
public class DemandeTraiteeExport {
    private int numSemaine;
    private long idSalle;
    private int effectif;
    private float montant;
    private long idClient;

    public long getIdClient() {
        return idClient;
    }

    public int getNumSemaine() {
        return numSemaine;
    }

    public long getIdSalle() {
        return idSalle;
    }

    public int getEffectif() {
        return effectif;
    }

    public float getMontant() {
        return montant;
    }
    
    
}
