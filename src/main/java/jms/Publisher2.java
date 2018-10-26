package jms;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.DeliveryMode;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSSessionMode;
import javax.jms.MapMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import entities.Product;

public class Publisher2 {
	

	
    public void activateTopic(List<Product> temp) throws Exception
    {
        InitialContext ctx=new InitialContext();  
        TopicConnectionFactory f=(TopicConnectionFactory)ctx.lookup("myTopicConnectionFactory");  
        TopicConnection con=f.createTopicConnection();  
        con.start();  
        
        TopicSession ses=con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);  
        Topic t=(Topic)ctx.lookup("myTopic");  
        TopicSubscriber receiver=ses.createSubscriber(t);
    	JMSContext context = f.createContext(JMSContext.AUTO_ACKNOWLEDGE);
		context.createProducer().setProperty("topicUser", "myTopic").send(t, temp.get(0));
        
           // get the initial context
      // InitialContext ctx = new InitialContext();
                                                                          
       // lookup the topic object
                                                                           
       // lookup the topic connection factory
     //  TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.
         //  lookup("myTopicConnectionFactory");
                                     
       
       // create a topic connection
      // TopicConnection topicConn = connFactory.createTopicConnection();
                                                                           
       // create a topic session
      // TopicSession topicSession = topicConn.createTopicSession(false,
        //   Session.AUTO_ACKNOWLEDGE);
                                                                           
       // create a topic publisher
		// MapMessage msg = topicSession.createMapMessage();
		// TopicPublisher publisher = topicSession.createPublisher(topic); 
		// msg.setString("User", "DUDE");
		// msg.setString("Message", "Grautlerer, du vant! Høyeste bid var på: ");                                                                
		//publisher.publish(msg); 

       
       // close the topic connection
       //topicConn.close();
  
    }

}
