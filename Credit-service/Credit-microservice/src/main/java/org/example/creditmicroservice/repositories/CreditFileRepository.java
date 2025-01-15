package org.example.creditmicroservice.repositories;

import org.example.creditmicroservice.entities.CreditFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditFileRepository extends JpaRepository<CreditFile, Long> {
}