package com.pwc.ecasofond.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "profession_types")
public class ProfessionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "name",
            unique = true
    )
    private String name;
}
