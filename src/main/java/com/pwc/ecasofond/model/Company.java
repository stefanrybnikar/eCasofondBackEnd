package com.pwc.ecasofond.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "name",
            length = 100,
            unique = true,
            nullable = false
    )
    private String name;
}
