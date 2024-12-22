package com.main.clientservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
public class ClientEntity {
    @Id
    @Column(name = "cin", nullable = false)
    private Long CIN;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(precision = 10, scale = 2)
    private Number monthlySalary;
}