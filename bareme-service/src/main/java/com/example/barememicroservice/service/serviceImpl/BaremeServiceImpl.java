package com.example.barememicroservice.service.serviceImpl;

import com.example.barememicroservice.dto.BaremeDto;
import com.example.barememicroservice.entity.Bareme;
import com.example.barememicroservice.mapper.Impl.BaremeMapper;
import com.example.barememicroservice.repository.BaremeRepository;
import com.example.barememicroservice.service.BaremeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class BaremeServiceImpl implements BaremeService {
    private final BaremeRepository baremeRepository;

    private final BaremeMapper baremeMapper;

    public BaremeServiceImpl(BaremeRepository baremeRepository, BaremeMapper baremeMapper) {
        this.baremeRepository = baremeRepository;
        this.baremeMapper = baremeMapper;
    }

    @Override
    public BaremeDto addBareme(BaremeDto baremeDto) {
        Bareme entity = baremeMapper.toEntity(baremeDto);
        Bareme savedEntity = baremeRepository.save(entity);
        return baremeMapper.toDto(savedEntity);
    }

    @Override
    public Page<BaremeDto> getAllBaremes(int page, int size) {
        Page<Bareme> baremes = baremeRepository.findAll(PageRequest.of(page, size));
        List<BaremeDto> dtos = baremeMapper.toDto(baremes.getContent());
        return new PageImpl<>(dtos, baremes.getPageable(), baremes.getTotalElements());
    }

    @Override
    public BaremeDto getBaremeById(Long id) {
        Bareme entity = baremeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barème not found with ID: " + id));
        return baremeMapper.toDto(entity);
    }

    @Override
    public BaremeDto updateBareme(BaremeDto baremeDto) {
        Bareme entity = baremeMapper.toEntity(baremeDto);
        if (!baremeRepository.existsById(entity.getId())) {
            throw new RuntimeException("Barème not found with ID: " + entity.getId());
        }
        Bareme updatedEntity = baremeRepository.save(entity);
        return baremeMapper.toDto(updatedEntity);
    }

    @Override
    public void deleteBareme(Long id) {
        if (!baremeRepository.existsById(id)) {
            throw new RuntimeException("Barème not found with ID: " + id);
        }
        baremeRepository.deleteById(id);
    }

    @Override
    public BaremeDto findOptimalBareme(int duree, double montant) {
        List<Bareme> applicableBaremes = baremeRepository
                .findByDureeMinimaleLessThanEqualAndDureeMaximaleGreaterThanEqualAndMontantMinimalLessThanEqualAndMontantMaximalGreaterThanEqual(
                        duree, duree, montant, montant);
        Bareme optimalBareme = applicableBaremes.stream()
                .min(Comparator.comparingDouble(Bareme::getTauxNominal))
                .orElseThrow(() -> new RuntimeException("No suitable barème found."));
        return baremeMapper.toDto(optimalBareme);
    }
}
