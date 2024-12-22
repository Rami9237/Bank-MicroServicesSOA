package com.main.clientservice.repository;

import com.main.clientservice.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientEntityRepository extends JpaRepository<ClientEntity, Long> {


}