package com.main.clientservice.repository;

import com.main.clientservice.dto.PageableDto;
import com.main.clientservice.entity.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientEntityRepository extends JpaRepository<ClientEntity, Long> {
    Page<ClientEntity> findByBlacklistedTrue(Pageable pageable);

}