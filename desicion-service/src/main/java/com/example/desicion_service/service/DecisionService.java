// Service interface
package com.example.desicion_service.service;

import com.example.desicion_service.dto.DecisionDto;
import com.example.desicion_service.dto.ScoringResultDTO;

public interface DecisionService {
    DecisionDto processDecision(ScoringResultDTO scoringResult);
    DecisionDto getDecisionByReference(String referenceDemande);
}