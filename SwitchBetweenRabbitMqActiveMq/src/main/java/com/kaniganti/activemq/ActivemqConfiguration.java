
package com.kaniganti.activemq;

import static org.apache.camel.component.jms.JmsComponent.jmsComponentAutoAcknowledge;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ThreadPoolRejectedPolicy;

import org.apache.camel.spi.ThreadPoolProfile;
import org.apache.camel.spring.boot.CamelContextConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
@Profile("active")
@Configuration
@EnableScheduling
public class ActivemqConfiguration {

    public static final String DEFAULT_PROFILE = "CVL_PROFILE";
    public static final String ACTIVEMQ = "activemq";
    @Bean
    CamelContextConfiguration contextConfiguration(@Value("${activemq.username}") String user,
                                                   @Value("${activemq.password}") String password,
                                                   @Value("${activemq.broker.url}") String url) {
        return new CamelContextConfiguration() {

            @Override
            public void beforeApplicationStart(CamelContext context) {
                // your custom configuration goes here
                ThreadPoolProfile threadPoolProfile = new ThreadPoolProfile();
                threadPoolProfile.setId(DEFAULT_PROFILE);
                threadPoolProfile.setPoolSize(10);
                threadPoolProfile.setMaxPoolSize(15);
                threadPoolProfile.setMaxQueueSize(250);
                threadPoolProfile.setKeepAliveTime(25L);
                threadPoolProfile.setRejectedPolicy(ThreadPoolRejectedPolicy.Abort);
                context.getExecutorServiceManager().registerThreadPoolProfile(threadPoolProfile);
                ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
                context.addComponent(ACTIVEMQ, jmsComponentAutoAcknowledge(connectionFactory));
               // com.rabbitmq.client.ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
               // factory.setPassword("");
               // factory.setHost("localhost");
               // factory.setPort(5672);
               // RabbitTemplate t;
               
              //  context.addComponent(ACTIVEMQ, jmsComponentAutoAcknowledge(factory));

            }

        };
    }
  //The below method is for future purpose if we need to monitor the ActiveMQ  
   // @Scheduled(fixedRate = 10000)
    public void scheduleTaskWithFixedRate() throws Exception {
      
        monitor();
    }
    public void monitor() throws Exception {
    	try {
    	    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
    	    Connection conn = factory.createConnection("admin", "admin");
    	    Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
    	   System.out.println("Success");
    	} catch (JMSException e) {
    	   System.out.println("Error");
    	   
    	}
   
    }
}
