package com.scoring.scoringservice.dto;

import com.scoring.scoringservice.enums.ScoreEvaluation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoreResponse {
    private String dossierId;
    private Integer score;
    private ScoreEvaluation evaluation;
    private Date calculationDate;
}
