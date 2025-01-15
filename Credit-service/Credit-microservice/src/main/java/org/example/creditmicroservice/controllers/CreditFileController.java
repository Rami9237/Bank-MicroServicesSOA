package org.example.creditmicroservice.controllers;

import org.example.creditmicroservice.DTOs.BaremeDto;
import org.example.creditmicroservice.DTOs.ClientDto;
import org.example.creditmicroservice.DTOs.CreditFileDto;
import org.example.creditmicroservice.DTOs.PageableDto;
import org.example.creditmicroservice.services.CreditFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/credits")
public class CreditFileController {
    private final CreditFileService creditFileService;

    private final RestTemplate restTemplate;

    @Value("${bareme.service.endpoint}")
    private String baremeServiceEndpoint;

    @Value("${client.service.endpoint}")
    private String clientServiceEndpoint;

    public CreditFileController(CreditFileService creditFileService, RestTemplate restTemplate) {
        this.creditFileService = creditFileService;
        this.restTemplate = restTemplate;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CreditFileDto> getCreditFile(@PathVariable Long id) {
        CreditFileDto creditFile = creditFileService.getCreditFileById(id);
        return ResponseEntity.ok(creditFile);
    }

    @GetMapping
    public ResponseEntity<Page<CreditFileDto>> getCreditFiles(Pageable pageable) {
        PageableDto pageableDto = new PageableDto(pageable.getPageNumber(), pageable.getPageSize());
        Page<CreditFileDto> creditFiles = creditFileService.getCreditFiles(pageableDto);
        return ResponseEntity.ok(creditFiles);
    }

    @PostMapping
    public ResponseEntity<CreditFileDto> createCreditFile(@RequestBody CreditFileDto creditFileDto) {
        BaremeDto baremeDto = fetchBaremeInfo(creditFileDto.getCreditSum(), creditFileDto.getCreditDuration());
        creditFileDto.setInterestRate(baremeDto.getTauxNominal()*creditFileDto.getCreditSum());
        creditFileDto.setIdScale(baremeDto.getId());
        creditFileDto.setMonthlyPayment((creditFileDto.getInterestRate()+ creditFileDto.getCreditSum())/creditFileDto.getCreditDuration());
        CreditFileDto createdCreditFile = creditFileService.addCreditFile(creditFileDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCreditFile);
    }

    @PutMapping
    public ResponseEntity<CreditFileDto> updateCreditFile(@RequestBody CreditFileDto creditFileDto) {
        CreditFileDto updatedCreditFile = creditFileService.updateCreditFile(creditFileDto);
        return ResponseEntity.ok(updatedCreditFile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditFile(@PathVariable Long id) {
        creditFileService.deleteCreditFileById(id);
        return ResponseEntity.noContent().build();
    }

    BaremeDto fetchBaremeInfo (double creditSum, int creditDuration) {
        String endpoint = baremeServiceEndpoint + "?creditSum=" + creditSum + "&creditDuration=" + creditDuration;
        return restTemplate.getForObject(endpoint, BaremeDto.class);
    }

    ClientDto fetchClientInfo(String cin) {
        String endpoint = clientServiceEndpoint+ "/" + cin;
        return restTemplate.getForObject(endpoint, ClientDto.class);
    }

}

