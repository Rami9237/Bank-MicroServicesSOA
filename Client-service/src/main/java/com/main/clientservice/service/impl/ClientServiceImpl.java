package com.main.clientservice.service.impl;

import com.main.clientservice.dto.ClientEntityDto;
import com.main.clientservice.dto.PageableDto;
import com.main.clientservice.entity.ClientEntity;
import com.main.clientservice.repository.ClientEntityRepository;
import com.main.clientservice.service.ClientService;
import com.main.clientservice.mapper.impl.ClientMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientEntityRepository ClientRepository;
    private final ClientMapper ClientMapper;

    public ClientServiceImpl(ClientEntityRepository ClientRepository, ClientMapper ClientMapper) {
        this.ClientRepository = ClientRepository;
        this.ClientMapper = ClientMapper;
    }

    @Override
    public ClientEntityDto registerClient(ClientEntityDto ClientEntityDto) {
        ClientEntity ClientEntity = ClientMapper.toEntity(ClientEntityDto);
        ClientEntity Client = ClientRepository.save(ClientEntity);
        return ClientMapper.toDto(Client);
    }

    @Override
    public Page<ClientEntityDto> getAllClient(PageableDto pageableDto) {
        Page<ClientEntity> ClientEntityPage = ClientRepository.findAll(pageableDto);
        List<ClientEntityDto> ClientEntityDtos = ClientMapper.toDto(ClientEntityPage.getContent());
        return new PageImpl<>(ClientEntityDtos, pageableDto, ClientEntityPage.getTotalElements());
    }

    @Override
    public ClientEntityDto getClientById(Long id) {
        ClientEntity ClientEntity = ClientRepository.findById(id).orElse(null);
        return ClientMapper.toDto(ClientEntity);
    }

    @Override
    public ClientEntityDto updateClient(ClientEntityDto ClientEntityDto) {
        if(!ClientRepository.existsById(ClientEntityDto.getCIN())){
            return null;
        }
        ClientEntity ClientEntity = ClientMapper.toEntity(ClientEntityDto);
        ClientRepository.save(ClientEntity);
        return ClientMapper.toDto(ClientEntity);
    }

    @Override
    public void deleteClient(Long id) {
        ClientRepository.deleteById(id);
    }
}