/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.jms;
import com.google.gson.Gson;
import fr.miage.tlse.business.GestionCommerciale;
import fr.miage.tlse.exceptions.FormationNotFoundException;
import fr.miage.tlse.export.DemandeExport;
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
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "DemandesAValider")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})

/**
 * Reception des demandes à valider de la part de l'application Cliente
 */
public class ReceiveDemandesAValider implements MessageListener{

    private GestionCommerciale gestionCommerciale;
    
    private Gson gson;
    
    public ReceiveDemandesAValider(){
        gson = new Gson();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String json = ((TextMessage) message).getText();
                DemandeExport demande = this.gson.fromJson(json, DemandeExport.class);
                try {
                    this.gestionCommerciale.verifierFormation(demande.getIdDemande());
                    System.out.println("La demande vient d'être récupérée.");
                } catch (FormationNotFoundException ex) {
                    Logger.getLogger(ReceiveDemandesAValider.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (JMSException ex) {
                Logger.getLogger(ReceiveDemandesAValider.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (message != null) {
            System.out.println("Echec de réception du message");
        }
    }
}
