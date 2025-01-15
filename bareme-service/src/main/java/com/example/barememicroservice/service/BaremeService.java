package com.example.barememicroservice.service;

import com.example.barememicroservice.dto.BaremeDto;
import org.springframework.data.domain.Page;

public interface BaremeService {
    BaremeDto addBareme(BaremeDto baremeDto);

    Page<BaremeDto> getAllBaremes(int page, int size);

    BaremeDto getBaremeById(Long id);

    BaremeDto updateBareme(BaremeDto baremeDto);

    void deleteBareme(Long id);

    BaremeDto findOptimalBareme(int duree, double montant);
}
