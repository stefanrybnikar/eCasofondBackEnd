package com.pwc.ecasofond.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "username",
            length = 50,
            unique = true,
            nullable = false
    )
    private String username;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @Column(
            name = "email",
            length = 100,
            unique = true,
            nullable = false
    )
    private String email;

    @Column(
            name = "display_name",
            length = 100,
            nullable = false
    )
    private String displayName;

    @Column(
            name = "company_id"
    )
    private Long companyId;

    @Column(
            name = "role_id",
            nullable = false
    )
    private Long roleId;
}
