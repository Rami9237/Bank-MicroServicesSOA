package com.main.clientservice.service;

import com.main.clientservice.dto.ClientEntityDto;
import com.main.clientservice.dto.PageableDto;
import org.springframework.data.domain.Page;

public interface ClientService {
    ClientEntityDto registerClient(ClientEntityDto ClientEntityDto);
    Page<ClientEntityDto> getAllClient(PageableDto pageableDto);
    ClientEntityDto getClientById(Long id);
    ClientEntityDto updateClient(ClientEntityDto ClientEntityDto);
    void deleteClient(Long id);

    Page<ClientEntityDto> getBlacklistedClients(PageableDto pageableDto);

    public ClientEntityDto blacklistClient(Long id);

    public ClientEntityDto unblacklistClient(Long id);
}
