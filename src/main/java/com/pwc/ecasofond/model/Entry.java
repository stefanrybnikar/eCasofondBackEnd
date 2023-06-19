package com.pwc.ecasofond.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "entries")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "user_id",
            nullable = false
    )
    private Long userId;

    @Column(
            name = "type_id",
            nullable = false
    )
    private Long typeId;

    @Column(
            name = "description"
    )
    private String description;

    @Column(
            name = "hour_count",
            nullable = false
    )
    private Integer hourCount;

    @CreationTimestamp
    @Column(
            name = "created"
    )
    private Timestamp created;

    @UpdateTimestamp
    @Column(
            name = "updated"
    )
    private Timestamp updated;

    @Column(
            name = "day",
            nullable = false
    )
    private Date day;
}
