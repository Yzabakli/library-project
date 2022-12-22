package com.abakli.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "admins")
@Where(clause = "is_deleted = false")
public class Admin extends User {


}