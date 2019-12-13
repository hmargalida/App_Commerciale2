/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.jms;

import com.google.gson.Gson;
import fr.miage.tlse.export.DemandesFormationsValideesExport;
import fr.miage.tlse.export.FormationExport;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author Sylvia
 */
@Stateless
public class SendFormationTopic implements SendFormationTopicLocal {

    /**
     * Nom du Topic recherché.
     */
    @Resource(mappedName = "FormationTopic")
    private Topic FormationTopic;
    
    private Gson gson;

    /**
     * contexte JMS. Injection auto par serveur d'appli.
     */
    @Inject
    @JMSConnectionFactory("ConnectionFactory")
    private JMSContext context;

    public SendFormationTopic() {
        gson=new Gson();

    }

    @Override
    public void sendDemandesFormationsValidees(FormationExport formation, String niveau) {
        try {
            JMSProducer producer = context.createProducer();

            TextMessage mess = context.createTextMessage();
            mess.setJMSType(this.gson.toJson(formation));
            mess.setJMSType("FormationTopic");
            context.createProducer().send(FormationTopic, mess);
            System.out.println(formation + " envoyée.");

        } catch (JMSException ex) {
            Logger.getLogger(SendDemandesFormationsValidees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
