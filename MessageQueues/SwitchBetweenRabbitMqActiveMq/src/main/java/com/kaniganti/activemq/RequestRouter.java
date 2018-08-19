
package com.kaniganti.activemq;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.kaniganti.camel.RequestProcessor;




@Profile("active")
@Component

public class RequestRouter extends RouteBuilder {

	@Autowired
	RequestProcessor processor;
	
	@Profile("active")  
    @Override
    public void configure() throws Exception {

        from("activemq://RequestQueue").to("activemq://ResponseQueue");
              
    }
	
}
