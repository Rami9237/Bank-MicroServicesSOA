package com.scoring.scoringservice.service.impl;

import com.scoring.scoringservice.dto.ScoreResponse;
import com.scoring.scoringservice.dto.ScoringData;
import com.scoring.scoringservice.service.ScoringService;
import com.scoring.scoringservice.enums.ScoreEvaluation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Service
public class ScoringServiceImpl implements ScoringService {
    private final RedisTemplate<String, ScoreResponse> redisTemplate;
    private static final String SCORE_KEY_PREFIX = "score:";
    private static final long SCORE_TTL_HOURS = 1;

    public ScoringServiceImpl(RedisTemplate<String, ScoreResponse> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ScoreResponse calculateAndStoreScore(ScoringData scoringData, String dossierId, Boolean isBlacklisted) {
        ScoreResponse scoreResponse;

        if (isBlacklisted) {
            scoreResponse = createRejectedScore(dossierId);
        } else {
            int score = calculateScore(scoringData);
            scoreResponse = ScoreResponse.builder()
                    .dossierId(dossierId)
                    .score(score)
                    .evaluation(score < 50 ? ScoreEvaluation.RED : ScoreEvaluation.GREEN)
                    .calculationDate(new Date())
                    .build();
        }
        storeScore(scoreResponse);

        return scoreResponse;
    }

    @Override
    public ScoreResponse getScore(String dossierId) {
        String key = SCORE_KEY_PREFIX + dossierId;
        return redisTemplate.opsForValue().get(key);
    }

    private void storeScore(ScoreResponse score) {
        String key = SCORE_KEY_PREFIX + score.getDossierId();
        redisTemplate.opsForValue().set(key, score, Duration.ofHours(SCORE_TTL_HOURS));
    }

    private int calculateScore(ScoringData request) {
        int score = 0;

        // Salary criteria
        if (request.getSalary() > 2000) {
            score += 20;
        } else if (request.getSalary() > 1000) {
            score += 10;
        }

        // Contract type criteria
        if ("CDI".equals(request.getContractType())) {
            score += 30;
        }

        // Payment ratio criteria
        double paymentRatio = request.getMonthlyPayment() / request.getSalary();
        if (paymentRatio < 0.45) {
            score += 50;
        }

        return score;
    }

    private ScoreResponse createRejectedScore(String dossierId) {
        return ScoreResponse.builder()
                .dossierId(dossierId)
                .score(0)
                .evaluation(ScoreEvaluation.RED)
                .calculationDate(new Date())
                .build();
    }
}