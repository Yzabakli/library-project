package com.abakli.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books")
@Where(clause = "is_deleted = false")
public class Book extends BaseEntity {

    private String title;
    private String edition;
    private String author;
    private String publisher;
    private Integer copies;
    private String source;
    private BigDecimal cost;
    private String remarks;
}