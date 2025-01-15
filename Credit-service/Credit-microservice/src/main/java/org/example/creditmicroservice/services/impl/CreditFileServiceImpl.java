package org.example.creditmicroservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.creditmicroservice.DTOs.CreditFileDto;
import org.example.creditmicroservice.DTOs.PageableDto;
import org.example.creditmicroservice.entities.CreditFile;
import org.example.creditmicroservice.mappers.impl.FileMapper;
import org.example.creditmicroservice.repositories.CreditFileRepository;
import org.example.creditmicroservice.services.CreditFileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditFileServiceImpl implements CreditFileService {

    private final CreditFileRepository creditFileRepository;
    private final FileMapper fileMapper;

    public CreditFileServiceImpl(CreditFileRepository creditFileRepository, FileMapper fileMapper) {
        this.creditFileRepository = creditFileRepository;
        this.fileMapper = fileMapper;
    }

    @Override
    public CreditFileDto getCreditFileById(long id) {
        CreditFile file = creditFileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit file not found with id: " + id));
        return fileMapper.toDto(file);
    }

    @Override
    public Page<CreditFileDto> getCreditFiles(PageableDto pageableDto) {
        Pageable pageable = PageRequest.of(pageableDto.getPageNumber(), pageableDto.getPageSize());
        Page<CreditFile> files = creditFileRepository.findAll(pageable);
        return files.map(fileMapper::toDto);
    }

    @Override
    public CreditFileDto addCreditFile(CreditFileDto creditFileDto) {
        CreditFile fileEntity = fileMapper.toEntity(creditFileDto);
        CreditFile file = creditFileRepository.save(fileEntity);
        return fileMapper.toDto(file);
    }

    @Override
    public CreditFileDto updateCreditFile(CreditFileDto creditFileDto) {
        long id = creditFileDto.getId();
        CreditFile existingFile = creditFileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit file not found with id: " + id));
        CreditFile updatedFile = fileMapper.toEntity(creditFileDto);
        updatedFile.setId(existingFile.getId());
        CreditFile savedFile = creditFileRepository.save(updatedFile);
        return fileMapper.toDto(savedFile);
    }

    @Override
    public void deleteCreditFileById(long id) {
        if (!creditFileRepository.existsById(id)) {
            throw new RuntimeException("Credit file not found with id: " + id);
        }
        creditFileRepository.deleteById(id);
    }
}

