package com.main.clientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link ClientProductsEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientProductsEntityDto implements Serializable {
    Long id;
    ClientEntityDto clientId;
    Long productId;
}