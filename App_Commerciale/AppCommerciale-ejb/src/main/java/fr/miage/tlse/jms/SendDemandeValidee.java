/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.jms;

import com.google.gson.Gson;
import fr.miage.tlse.export.DemandeExport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author Sylvia
 */
/**
 * Envoi des demandes à valider à l'application cliente
 * @author Sylvia
 */
@Stateless
public class SendDemandeValidee implements SendDemandeValideeLocal {

    /**
     * Nom du Topic recherché.
     */
    @Resource(mappedName = "DemandeValidee")
    private Topic DemandeValidee;
    
    private Gson gson;

    /**
     * contexte JMS. Injection auto par serveur d'appli.
     */
    @Inject
    @JMSConnectionFactory("ConnectionFactory")
    private JMSContext context;

    public SendDemandeValidee() {
        gson=new Gson();
    }

    @Override
    public void sendDemande(DemandeExport demande, String niveau) {
        try {
            JMSProducer producer = context.createProducer();

            TextMessage mess = context.createTextMessage();
            mess.setJMSType(this.gson.toJson(demande));
            mess.setJMSType("DemandeValidee");
            context.createProducer().send(DemandeValidee, mess);
            System.out.println(demande + " envoyée.");

        } catch (JMSException ex) {
            Logger.getLogger(SendDemandeValidee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
