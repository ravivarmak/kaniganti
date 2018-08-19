package com.kaniganti.camel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import com.kaniganti.activemq.ActiveMqSender;
import com.kaniganti.rabbitmq.RabbitSender;

@Configuration

public class ResponseSender {
  private static final Logger logger = LoggerFactory.getLogger(ResponseSender.class);
  @Value("${spring.profiles.active}")
  private String activeProfile;
  @Autowired
  Environment properties;
  @Autowired(required = false)
  ActiveMqSender activemqSender;
  @Autowired(required = false)
  RabbitSender rabbitmqSender;  
  public String sendSuccessMessage(Object message) {
    try {
 
      logger.info("Active Profiles Rabbit? "+activeProfile.contains("rabbit"));
      if(activeProfile.contains("active")) {
    	  activemqSender.sendMsgToActiveMessageQueue(message);
      }
      else {
    	  rabbitmqSender.sendMessage(message);
      }
      
      return "Message sent Successfully.";
    } catch (Exception mainExc) {
      return "Error in sending Message : " + mainExc.getMessage();
    }
  } 

} 