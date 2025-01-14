package com.scoring.scoringservice.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ClientEntity {
    private Long CIN;
    private String firstName;
    private String lastName;
    private String contractType;
    private Date dateOfBirth;
    private double monthlySalary;
    private boolean blacklisted;
}