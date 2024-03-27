package com.neudesic.appointmentmanagementsystem.infrastructure.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfiguration {

    @Bean
    public RestTemplate getPatientDomainWebClient(){
        return new RestTemplate();
    }

}
