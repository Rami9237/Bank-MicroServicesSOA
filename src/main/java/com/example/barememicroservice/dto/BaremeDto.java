package com.example.barememicroservice.dto;

import com.example.barememicroservice.entity.Bareme;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Bareme}
 */
@Value
public class BaremeDto implements Serializable {
    Long id;
    double tauxNominal;
    int dureeMinimale;
    int dureeMaximale;
    double montantMinimal;
    double montantMaximal;
}