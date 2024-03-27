package com.neudesic.appointmentmanagementsystem.infrastructure.http;

import com.neudesic.appointmentmanagementsystem.controller.dto.GetPatientDomainResponse;
import com.neudesic.appointmentmanagementsystem.domain.entities.Patient;
import com.neudesic.appointmentmanagementsystem.domain.exceptions.AppointmentDomainException;
import com.neudesic.appointmentmanagementsystem.domain.valueobjects.PatientID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Service
@Slf4j
public class PatientDomainExternalServiceRetriever {

    @Autowired
    RestTemplate restTemplate;

   public Patient getPatientInfo(UUID patientID){
       try {
           // Set up the URL and parameters
           String url = "http://localhost:8081/getPatient/{id}";
           UUID patientId = patientID;
           URI uri = UriComponentsBuilder.fromUriString(url).buildAndExpand(patientId).toUri();

           // Perform the request and receive the ResponseEntity
           ResponseEntity<GetPatientDomainResponse> responseEntity = restTemplate.exchange(
                   uri,
                   HttpMethod.GET,
                   null,
                   GetPatientDomainResponse.class
           );

           HttpStatusCode statusCode = responseEntity.getStatusCode();
           if (statusCode.is2xxSuccessful()) {
               log.info("Patient found");
               return responseEntity.getBody().getEntity().get(0);
           } else {
               log.error("Error: {}", statusCode);
               throw new AppointmentDomainException("No patient found in the db");
           }
       } catch (Exception e) {
           throw new AppointmentDomainException("Error in calling patient domain service");
       }
   }

}
