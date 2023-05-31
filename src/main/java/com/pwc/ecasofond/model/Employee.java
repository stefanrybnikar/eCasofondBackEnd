package com.pwc.ecasofond.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Employees", schema = "public")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;
}
