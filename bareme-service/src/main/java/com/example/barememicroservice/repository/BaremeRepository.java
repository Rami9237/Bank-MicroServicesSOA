package com.example.barememicroservice.repository;

import com.example.barememicroservice.entity.Bareme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaremeRepository extends JpaRepository<Bareme, Long> {
    List<Bareme> findByDureeMinimaleLessThanEqualAndDureeMaximaleGreaterThanEqualAndMontantMinimalLessThanEqualAndMontantMaximalGreaterThanEqual(
            int duree, int duree2, double montant, double montant2);
}
