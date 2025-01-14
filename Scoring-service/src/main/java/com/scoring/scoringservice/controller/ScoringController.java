package com.scoring.scoringservice.controller;

import ch.qos.logback.core.net.server.Client;
import com.scoring.scoringservice.dto.ApiResponse;
import com.scoring.scoringservice.dto.ScoreResponse;
import com.scoring.scoringservice.dto.ScoringData;
import com.scoring.scoringservice.dto.ScoringRequest;
import com.scoring.scoringservice.entity.ClientEntity;
import com.scoring.scoringservice.service.ScoringService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/scoring")
public class ScoringController {

    private final ScoringService scoringService;

    private final RestTemplate restTemplate;

    @Value("${client.service.endpoint}")
    private String clientServiceEndpoint;

    public ScoringController(ScoringService scoringService, RestTemplate restTemplate) {
        this.scoringService = scoringService;
        this.restTemplate = restTemplate;
    }

    /**
     * Calculate and store the score based on the request.
     */
    @PostMapping("/calculate")
    public ResponseEntity<ApiResponse<ScoreResponse>> calculateScore(@RequestBody ScoringRequest request) {

        ClientEntity clientInfo = fetchClientInfo(request.getCin());
        boolean isBlacklisted = clientInfo.isBlacklisted();
        ScoringData scoringData = new ScoringData(clientInfo.getMonthlySalary(), clientInfo.getContractType(), request.getMonthlyPayment());
        ScoreResponse scoreResponse = scoringService.calculateAndStoreScore(scoringData, request.getDossierId(), isBlacklisted);

        ApiResponse<ScoreResponse> response = new ApiResponse<>(
                scoreResponse,
                "Score calculated successfully.",
                true
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieve a stored score by dossier ID.
     */
    @GetMapping("/{dossierId}")
    public ResponseEntity<ApiResponse<ScoreResponse>> getScore(@PathVariable String dossierId) {
        ScoreResponse scoreResponse = scoringService.getScore(dossierId);

        if (scoreResponse == null) {
            ApiResponse<ScoreResponse> response = new ApiResponse<>(
                    null,
                    "Score not found.",
                    false
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        ApiResponse<ScoreResponse> response = new ApiResponse<>(
                scoreResponse,
                "Score retrieved successfully.",
                true
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    ClientEntity fetchClientInfo(String cin) {
        String endpoint = clientServiceEndpoint+ "/" + cin;
        return restTemplate.getForObject(endpoint, ClientEntity.class);
    }

}
