// Repository interface
package com.example.desicion_service.repository;

import com.example.desicion_service.entity.DesicionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DecisionRepository extends JpaRepository<DesicionEntity, Long> {
    Optional<DesicionEntity> findByReferenceDemande(String referenceDemande);
}