// Entity class
package com.example.desicion_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "decisions")
@Data
public class DesicionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "reference_demande", nullable = false)
    private String referenceDemande;
    
    @Column(name = "date_decision", nullable = false)
    private LocalDateTime dateDecision;
    
    @Column(name = "statut_decision", nullable = false)
    private String statutDecision;
}
