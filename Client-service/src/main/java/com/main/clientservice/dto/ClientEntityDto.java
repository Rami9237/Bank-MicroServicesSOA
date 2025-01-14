package com.main.clientservice.dto;

import com.main.clientservice.entity.ClientEntity;
import com.main.clientservice.entity.ContractType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link ClientEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntityDto implements Serializable {
    private Long CIN;
    private String firstName;
    private String lastName;
    private ContractType contractType;
    private Date dateOfBirth;
    private Number monthlySalary;
    private Boolean blacklisted;
}