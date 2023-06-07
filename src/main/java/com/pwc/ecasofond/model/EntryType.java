package com.pwc.ecasofond.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "entry_types")
public class EntryType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "name",
            unique = true,
            nullable = false
    )
    private String name;
}
