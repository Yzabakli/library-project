package com.abakli.entity;

import com.abakli.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "borrower_records")
@Where(clause = "is_deleted = false")
public class BorrowerRecord extends BaseEntity {

    @ManyToOne
    private Book book;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Staff staff;

    private Integer numberOfCopies;
    private LocalDate releaseDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public BorrowerRecord(Book book, Student student, Integer numberOfCopies, LocalDate releaseDate, LocalDate dueDate) {
        this.book = book;
        this.student = student;
        this.numberOfCopies = numberOfCopies;
        this.releaseDate = releaseDate;
        this.dueDate = dueDate;
    }
}