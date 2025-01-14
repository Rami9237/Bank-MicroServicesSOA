package com.scoring.scoringservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScoringRequest {
    private String dossierId;
    private String cin;
    private double monthlyPayment;
}