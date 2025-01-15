package org.example.creditmicroservice.DTOs;

import lombok.Data;

import java.util.Date;

@Data
public class ClientDto {

    private Long CIN;
    private String firstName;
    private String lastName;
    private String contractType;
    private Date dateOfBirth;
    private double monthlySalary;
    private boolean blacklisted;
}
