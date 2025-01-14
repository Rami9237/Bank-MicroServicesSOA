package com.scoring.scoringservice.service;

import com.scoring.scoringservice.dto.ScoreResponse;
import com.scoring.scoringservice.dto.ScoringData;
import com.scoring.scoringservice.dto.ScoringRequest;

public interface ScoringService {
    ScoreResponse getScore(String dossierId);
    ScoreResponse calculateAndStoreScore(ScoringData scoringData, String dossierId, Boolean isBlacklisted);
}
