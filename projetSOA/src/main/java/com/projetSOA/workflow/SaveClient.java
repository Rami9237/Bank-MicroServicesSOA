package com.projetSOA.workflow;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SaveClient implements JavaDelegate {
    private final String API_URL = "http://localhost:8002/api/v1/clients";
    private final RestTemplate restTemplate = new RestTemplate();
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String CIN = (String) delegateExecution.getVariable("cin");
        String firstName = (String) delegateExecution.getVariable("firstName");
        String lastName = (String) delegateExecution.getVariable("lastName");
        String ContractType = (String) delegateExecution.getVariable("contractType");
        String dateOfBirth = (String) delegateExecution.getVariable("dateOfBirth");
        Integer monthlySalary = (Integer) delegateExecution.getVariable("monthlySalary");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("cin", CIN);
        requestBody.put("firstName", firstName);
        requestBody.put("lastName", lastName);
        requestBody.put("contractType", ContractType);
        requestBody.put("dateOfBirth", dateOfBirth);
        requestBody.put("monthlySalary", monthlySalary);

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            // Make the POST request
            ResponseEntity<String> response = restTemplate.postForEntity(
                    API_URL,
                    request,
                    String.class
            );

            // Store the response in process variables if needed
            delegateExecution.setVariable("apiResponseStatus", response.getStatusCodeValue());
            delegateExecution.setVariable("apiResponseBody", response.getBody());

        } catch (Exception e) {
            // Handle any errors
            delegateExecution.setVariable("apiError", e.getMessage());
            throw e;
        }
    }
}
