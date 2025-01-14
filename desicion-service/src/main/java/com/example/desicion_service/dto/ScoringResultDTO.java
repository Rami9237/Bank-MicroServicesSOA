// DTO classes
package com.example.desicion_service.dto;

import java.sql.Date;

import com.example.desicion_service.enums.ScoreEvaluation;

import lombok.Data;


@Data
public class ScoringResultDTO {
    private String dossierId;
    private String score;
    private ScoreEvaluation evaluation;
    private Date calculationDate;
}