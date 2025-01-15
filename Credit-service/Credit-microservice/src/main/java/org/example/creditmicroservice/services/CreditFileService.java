package org.example.creditmicroservice.services;

import org.example.creditmicroservice.DTOs.CreditFileDto;
import org.example.creditmicroservice.DTOs.PageableDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CreditFileService {
    CreditFileDto addCreditFile(CreditFileDto creditFileDto);
    CreditFileDto updateCreditFile(CreditFileDto creditFileDto);
    CreditFileDto getCreditFileById(long id);
    Page<CreditFileDto> getCreditFiles(PageableDto pageableDto);
    void deleteCreditFileById(long id);
}
