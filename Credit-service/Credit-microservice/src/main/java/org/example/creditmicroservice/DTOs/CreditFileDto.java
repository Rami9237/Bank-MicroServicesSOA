package org.example.creditmicroservice.DTOs;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.example.creditmicroservice.entities.CreditFile}
 */
@Data
public class CreditFileDto implements Serializable {
    Long id;
    Long clientCIN;
    double creditSum;
    Long idScale;
    double interestRate;
    int creditDuration;
    double monthlyPayment;

    public CreditFileDto(Long id, Long clientCIN, double creditSum, Long idScale, double interestRate, int creditDuration, double monthlyPayment) {
        this.id = id;
        this.clientCIN = clientCIN;
        this.creditSum = creditSum;
        this.idScale = idScale;
        this.interestRate = interestRate;
        this.creditDuration = creditDuration;
        this.monthlyPayment = monthlyPayment;
    }
}