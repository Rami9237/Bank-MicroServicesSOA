package com.main.clientservice.controller;

import com.main.clientservice.dto.ApiResponse;
import com.main.clientservice.dto.ClientEntityDto;
import com.main.clientservice.dto.PageableDto;
import com.main.clientservice.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClientEntityDto>> registerClient(@RequestBody  ClientEntityDto clientEntityDto) {
        ClientEntityDto clientRegistered = clientService.registerClient(clientEntityDto);
        ApiResponse<ClientEntityDto> response = new ApiResponse<>(clientRegistered, "Client registered successfully.", true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ClientEntityDto getClient(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<ClientEntityDto>> updateClient(@PathVariable Long id, @RequestBody ClientEntityDto clientEntityDto) {
        clientEntityDto.setCIN(id);
        ClientEntityDto clientUpdated = clientService.updateClient(clientEntityDto);
        ApiResponse<ClientEntityDto> response = new ApiResponse<>(clientUpdated, "Client updated successfully.", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        ApiResponse<Void> response = new ApiResponse<>(null, "Client deleted successfully.", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ClientEntityDto>> getClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort
    ){
        PageableDto pageableDto = new PageableDto(page, size, sort);
        Page<ClientEntityDto> clientEntityDtos = clientService.getAllClient(pageableDto);
        return new ResponseEntity<>(clientEntityDtos, HttpStatus.OK);
    }

    @GetMapping("/blacklisted")
    public ResponseEntity<Page<ClientEntityDto>>  getBlacklistedClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort
    ) {
        PageableDto pageableDto = new PageableDto(page, size, sort);
        Page<ClientEntityDto> clientEntityDtos = clientService.getBlacklistedClients(pageableDto);
        return new ResponseEntity<>(clientEntityDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}/blacklist")
    public ResponseEntity<ApiResponse<ClientEntityDto>> blacklistClient(@PathVariable Long id) {
        ClientEntityDto clientEntityDto = clientService.blacklistClient(id);
        ApiResponse<ClientEntityDto> apiResponse= new ApiResponse<>(clientEntityDto, "Client blacklisted successfully", true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}/unblacklist")
    public ResponseEntity<ApiResponse<ClientEntityDto>> unblacklistClient(@PathVariable Long id) {
        ClientEntityDto clientEntityDto = clientService.unblacklistClient(id);
        ApiResponse<ClientEntityDto> apiResponse = new ApiResponse<>(clientEntityDto,"Client unblacklisted successfully", true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
