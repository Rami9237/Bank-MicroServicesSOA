package com.example.desicion_service.dto;

import lombok.Data;

@Data
public class ScoringResponseWrapperDTO {
    private ScoringResultDTO data;
    private String message;
    private boolean success;
}