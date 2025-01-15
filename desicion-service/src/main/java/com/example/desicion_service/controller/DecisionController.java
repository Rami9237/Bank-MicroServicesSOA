// Controller class
package com.example.desicion_service.controller;

import com.example.desicion_service.dto.DecisionDto;
import com.example.desicion_service.dto.ScoringResponseWrapperDTO;
import com.example.desicion_service.dto.ScoringResultDTO;
import com.example.desicion_service.service.DecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/decisions")
public class DecisionController {
    private final RestTemplate restTemplate;
    private final DecisionService decisionService;

    @Value("${scoring.service.endpoint}")
    private String scoringServiceEndpoint;

    public DecisionController(DecisionService decisionService, RestTemplate restTemplate) {
        this.decisionService = decisionService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/process/{id}")
    public ResponseEntity<DecisionDto> processDecision(@PathVariable String id) {
        ScoringResultDTO scoringResultDTO = fetchScoringResult(id);
        DecisionDto decision = decisionService.processDecision(scoringResultDTO);
        return ResponseEntity.ok(decision);
    }

    @GetMapping("/{referenceDemande}")
    public ResponseEntity<DecisionDto> getDecision(@PathVariable String referenceDemande) {
        return ResponseEntity.ok(decisionService.getDecisionByReference(referenceDemande));
    }

    private ScoringResultDTO fetchScoringResult(String dossierId) {
        String endpoint = scoringServiceEndpoint + "/" + dossierId;
        ResponseEntity<ScoringResponseWrapperDTO> response = restTemplate.getForEntity(
                endpoint,
                ScoringResponseWrapperDTO.class
        );

        if (response.getBody() != null && response.getBody().isSuccess()) {
            return response.getBody().getData();
        } else {
            throw new RuntimeException("Failed to fetch scoring result: " +
                    (response.getBody() != null ? response.getBody().getMessage() : "No response"));
        }
    }
}