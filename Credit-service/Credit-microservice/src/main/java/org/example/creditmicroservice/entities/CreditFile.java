package org.example.creditmicroservice.entities;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "credit_file")
public class CreditFile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(nullable = false)
    private Long clientCIN;
    @Column(nullable = false)
    private double creditSum;
    @Column(nullable = false)
    private Long idScale;
    @Column(nullable = false)
    private double interestRate;
    @Column(nullable = false)
    private int creditDuration;

    @Column(nullable = false)
    private double monthlyPayment;

}