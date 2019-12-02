/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.jms;

import javax.ejb.Local;

/**
 *
 * @author Sylvia
 */
@Local
public interface SendDemandesFormationsValideesLocal {
    public void sendDemandesFormationsValidees(String demande, String niveau);
}
