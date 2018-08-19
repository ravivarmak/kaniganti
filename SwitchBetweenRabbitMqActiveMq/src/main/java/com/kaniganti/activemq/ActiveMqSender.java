
package com.kaniganti.activemq;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("active")
@Configuration
public class ActiveMqSender {
	
@Autowired
ProducerTemplate producerTemplate;

//@EndpointInject(uri = "rabbitmq://localhost/reqExch?vhost=/&routingKey=request&queue=RequestQueue&autoDelete=false")
//private Endpoint rabbitMQEndpoint;


public String sendToQueue( String queue, Object req )  {
	

	producerTemplate.sendBody(queue,req);

	return "Success";
	
}
@Profile("active")
public String sendToRabbitMQueue( String queue, Object req ) {
	

	//producerTemplate.sendBody(rabbitMQEndpoint, "Kaniganti");

	return "Success";
	
}

public String sendToQueue( String queue, String req ) {
	
	producerTemplate.sendBody(queue,req);
	return "Success";
	
}
public void sendMsgToActiveMessageQueue(Object message) {
	producerTemplate.sendBody("activemq://ResponseQueue",message);
	
	
}

}
