/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kaniganti.rabbitmq;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * A simple Camel route that triggers from a timer and routes to RabbitMQ
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Profile("rabbit")
@Component
public class RequestRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    	String brokerURL = "rabbitmq://localhost/reqExch?vhost=/&routingKey=request&queue=RequestQueue&autoDelete=false";
        from(brokerURL)
        .log("Received Message: ${body}");
            }

}
