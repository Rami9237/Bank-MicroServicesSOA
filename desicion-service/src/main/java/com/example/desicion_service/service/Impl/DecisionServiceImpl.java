// Service implementation
package com.example.desicion_service.service.Impl;

import com.example.desicion_service.dto.DecisionDto;
import com.example.desicion_service.dto.ScoringResultDTO;
import com.example.desicion_service.entity.DesicionEntity;
import com.example.desicion_service.mapper.Impl.DecisionMapper;
import com.example.desicion_service.repository.DecisionRepository;
import com.example.desicion_service.service.DecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DecisionServiceImpl implements DecisionService {
    
    private final DecisionRepository decisionRepository;
    private final DecisionMapper decisionMapper;
    
    @Override
    public DecisionDto processDecision(ScoringResultDTO scoringResult) {
        DesicionEntity decision = new DesicionEntity();
        decision.setReferenceDemande(scoringResult.getDossierId());
        decision.setDateDecision(LocalDateTime.now());
        
        // Set decision status based on score color
        String statutDecision = "RED".equalsIgnoreCase(scoringResult.getEvaluation().toString()) 
            ? "Refus" 
            : "Acceptation";
        decision.setStatutDecision(statutDecision);
        
        DesicionEntity savedDecision = decisionRepository.save(decision);
        return decisionMapper.toDto(savedDecision);
    }
    
    @Override
    public DecisionDto getDecisionByReference(String referenceDemande) {
        return decisionRepository.findByReferenceDemande(referenceDemande)
            .map(decisionMapper::toDto)
            .orElseThrow(() -> new RuntimeException("Decision not found"));
    }
}