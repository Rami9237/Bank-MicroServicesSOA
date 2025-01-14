// DTO classes
package com.example.desicion_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DecisionDto {
    private Long id;
    private String referenceDemande;
    private LocalDateTime dateDecision;
    private String statutDecision;
}
