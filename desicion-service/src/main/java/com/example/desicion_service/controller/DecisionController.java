// Controller class
package com.example.desicion_service.controller;

import com.example.desicion_service.dto.DecisionDto;
import com.example.desicion_service.dto.ScoringResultDTO;
import com.example.desicion_service.service.DecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/decisions")
@RequiredArgsConstructor
public class DecisionController {
    
    private final DecisionService decisionService;
    
    @PostMapping("/process")
    public ResponseEntity<DecisionDto> processDecision(@RequestBody ScoringResultDTO scoringResult) {
        return ResponseEntity.ok(decisionService.processDecision(scoringResult));
    }
    
    @GetMapping("/{referenceDemande}")
    public ResponseEntity<DecisionDto> getDecision(@PathVariable String referenceDemande) {
        return ResponseEntity.ok(decisionService.getDecisionByReference(referenceDemande));
    }
}