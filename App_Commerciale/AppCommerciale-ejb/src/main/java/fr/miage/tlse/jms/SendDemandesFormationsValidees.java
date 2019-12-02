/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.jms;

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
import javax.jms.ObjectMessage;

/**
 *
 * @author Sylvia
 */
@Stateless
public class SendDemandesFormationsValidees implements SendDemandesFormationsValideesLocal {

    /**
     * Nom de la Queue recherchée.
     */
    @Resource(mappedName = "DemandesFormationsValidees")
    private Queue DemandesFormationsValidees;

    /**
     * contexte JMS. Injection auto par serveur d'appli.
     */
    @Inject
    @JMSConnectionFactory("ConnectionFactory")
    private JMSContext context;

    public SendDemandesFormationsValidees() {

    }

    @Override
    public void sendDemandesFormationsValidees(String demande, String niveau) {
        try {
            JMSProducer producer = context.createProducer();

            ObjectMessage mess = context.createObjectMessage();
            mess.setJMSType(niveau);
            mess.setObject(demande);
            context.createProducer().send(DemandesFormationsValidees, mess);
            System.out.println(demande + " envoyée.");

        } catch (JMSException ex) {
            Logger.getLogger(SendDemandesFormationsValidees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
