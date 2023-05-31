package com.pwc.ecasofond.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Companies", schema = "public")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
