/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.jms;

import com.google.gson.Gson;
import fr.miage.tlse.business.GestionCommerciale;
import fr.miage.tlse.export.DemandeTraiteeExport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 *
 * @author Sylvia
 */

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "DemandesTraitees")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
/**
 * Réception des demandes traitées par l'application Formation
 */
public class ReceiveDemandesTraitees implements MessageListener{
    private GestionCommerciale gestionCommerciale;
    private Gson gson;
    
    public ReceiveDemandesTraitees(){
        gson = new Gson();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String json = ((TextMessage) message).getText();
                DemandeTraiteeExport demandeTraitee = this.gson.fromJson(json, DemandeTraiteeExport.class);
                this.gestionCommerciale.envoiCompteRendu(demandeTraitee.getIdClient());
                System.out.println("Demandes traitées reçues.");

            } catch (JMSException ex) {
                Logger.getLogger(ReceiveDemandesTraitees.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (message != null) {
            System.out.println("Echec de réception du message");
        }
    }
}
