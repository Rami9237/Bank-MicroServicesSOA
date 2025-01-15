package com.projetSOA.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class CreateCredit implements JavaDelegate {
    private final String API_URL = "http://localhost:8002/api/v1/credits";
    private final RestTemplate restTemplate = new RestTemplate();
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String CIN = (String) delegateExecution.getVariable("client_cin");
        double creditSum = (double) delegateExecution.getVariable("credit_sum");
        int creditDuration = (int) delegateExecution.getVariable("credit_duration");


        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("cin", CIN);
        requestBody.put("creditSum", creditSum);
        requestBody.put("creditDuration", creditDuration);


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
            delegateExecution.setVariable("creditApiResponseStatus", response.getStatusCodeValue());
            delegateExecution.setVariable("creditApiResponseBody", response.getBody());

        } catch (Exception e) {
            // Handle any errors
            delegateExecution.setVariable("apiError", e.getMessage());
            throw e;
        }
    }
}
