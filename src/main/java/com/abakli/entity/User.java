package com.abakli.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class User extends BaseEntity {

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String contact;
    private String password;

    @ManyToOne
    private Role role;
}