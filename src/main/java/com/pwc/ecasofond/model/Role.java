package com.pwc.ecasofond.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "role_types")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "name",
            unique = true,
            nullable = false
    )
    private String name;

    @Column(
            name = "level",
            nullable = false,
            unique = true
    )
    private Integer level;

    @Column(
            name = "write",
            nullable = false
    )
    private Boolean write;
}
