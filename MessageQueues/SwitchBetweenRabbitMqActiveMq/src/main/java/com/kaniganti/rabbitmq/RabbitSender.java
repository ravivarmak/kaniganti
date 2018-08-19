package com.kaniganti.rabbitmq;

import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
@Profile("rabbit")
public class RabbitSender {
	
	@Autowired
	ProducerTemplate producerTemplate;

	@EndpointInject(uri = "rabbitmq://localhost/respExch?vhost=/&routingKey=response&queue=ResponseQueue&autoDelete=false")
	private Endpoint rabbitMQEndpoint;
public String sendMessage(Object template) {
		
		producerTemplate.sendBody(rabbitMQEndpoint, "Kaniganti");
		return "Message Sent Successfully";
	}

}
