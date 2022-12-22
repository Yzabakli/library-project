package com.abakli.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Where(clause = "is_deleted = false")
public class Staff extends User {

    private String contactNumber;
    private String address;
    private com.abakli.enums.Type Type;
}