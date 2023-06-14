package com.pwc.ecasofond.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "profession_types_entry_types")
public class ProfessionTypeEntryType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "profession_type_id",
            nullable = false
    )
    private Long professionId;

    @Column(
            name = "entry_type_id",
            nullable = false
    )
    private Long entryTypeId;
}
