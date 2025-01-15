package com.projetSOA.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.h2.util.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class ScoreCredit implements JavaDelegate {

    private final String Credits_URL = "http://localhost:8002/api/v1/credits";
    private final String Scoring_URL = "http://localhost:8002/api/scoring";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        // Retrieve the response body stored in the process variable
        String creditApiResponseBody = (String) delegateExecution.getVariable("creditApiResponseBody");

        if (creditApiResponseBody == null || creditApiResponseBody.isEmpty()) {
            throw new IllegalStateException("creditApiResponseBody is not available.");
        }

        // Parse the JSON response

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);



    }
}
