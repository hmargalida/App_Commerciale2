/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.jms;

import com.google.gson.Gson;
import fr.miage.tlse.export.CatalogueExport;
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
 * Envoie du catalogue à l'application Cliente
 * @author Sylvia
 */
@Stateless
public class SendCatalogue implements SendCatalogueLocal {

    /**
     * Nom du Topic recherché.
     */
    @Resource(mappedName = "Catalogue")
    private Topic catalogueTopic;
    
    private Gson gson;

    /**
     * contexte JMS. Injection auto par serveur d'appli.
     */
    @Inject
    @JMSConnectionFactory("ConnectionFactory")
    private JMSContext context;

    public SendCatalogue() {
        gson=new Gson();
    }

    @Override
    public void sendCatalogue(CatalogueExport catalogue) {
        try {
            JMSProducer producer = context.createProducer();

            TextMessage mess = context.createTextMessage();
            mess.setText(this.gson.toJson(catalogue));
            mess.setJMSType("Catalogue");
            context.createProducer().send(catalogueTopic, mess);
            System.out.println(catalogue + " envoyé.");

        } catch (JMSException ex) {
            Logger.getLogger(SendCatalogue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
