/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.jms;

import com.google.gson.Gson;
import fr.miage.tlse.export.DemandesFormationsValideesExport;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 *
 * @author Sylvia
 */
/**
 * Envoi des demandes formations validées à l'application formation 
 * @author Sylvia
 */
@Stateless
public class SendDemandesFormationsValidees implements SendDemandesFormationsValideesLocal {

    /**
     * Nom de la Queue recherchée.
     */
    @Resource(mappedName = "DemandesFormationsValidees")
    private Queue DemandesFormationsValidees;
    
    private Gson gson;

    /**
     * contexte JMS. Injection auto par serveur d'appli.
     */
    @Inject
    @JMSConnectionFactory("ConnectionFactory")
    private JMSContext context;

    public SendDemandesFormationsValidees() {
        gson=new Gson();

    }

    @Override
    public void sendDemandesFormationsValidees(DemandesFormationsValideesExport demande, String niveau) {
        try {
            JMSProducer producer = context.createProducer();

            TextMessage mess = context.createTextMessage();
            mess.setJMSType(this.gson.toJson(demande));
            mess.setJMSType("DemandesFormationsValidees");
            //context.createProducer().send(DemandesFormationsValidees, mess);
            System.out.println(demande + " envoyée.");

        } catch (JMSException ex) {
            Logger.getLogger(SendDemandesFormationsValidees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
