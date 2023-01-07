package com.example.crud.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "users")
@Getter
public class User {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    @Email
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;
}
