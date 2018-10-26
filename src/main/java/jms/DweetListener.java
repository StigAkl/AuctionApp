package jms;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.google.gson.JsonObject;

import entities.Bid;
import entities.Product;

/**
 * @Author Alejandro Rodriguez Dat250
 * 
 * Listener triggered when a tweet is sent to the Topic JMS. It filters
 * the topic for "dweet"
 * 
 */

@MessageDriven(mappedName = "myTopic", activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "myTopic"),
		@ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "myTopic"),
		@ActivationConfigProperty(propertyName = "clientId", propertyValue = "myTopic"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "topicUser = 'myTopic'") })
public class DweetListener implements MessageListener {

	@Override
	public void onMessage(Message message) {

		try {
			Product product = message.getBody(Product.class);
			JsonObject json = new JsonObject();
			json.addProperty("User", "Karlson paa taket");
			json.addProperty("Message", "Gratulerer, du vant!");
			
			Logger logger = Logger.getLogger(getClass().getName());
			logger.info("DTWEET User: " + product.getName()); 
			logger.info("DTWEET Message: " + "Heihei");
			logger.info("DTWEET: Sending tweet to dweet...");
			logger.info("DTWEET JSON: " + json);
			try {
				DweetConnection dc = new DweetConnection();
				dc.publish(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}