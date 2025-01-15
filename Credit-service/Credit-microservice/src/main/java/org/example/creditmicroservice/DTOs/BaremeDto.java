package org.example.creditmicroservice.DTOs;

import lombok.Data;

@Data
public class BaremeDto {
    Long id;
    double tauxNominal;
    int dureeMinimale;
    int dureeMaximale;
    double montantMinimal;
    double montantMaximal;
}
