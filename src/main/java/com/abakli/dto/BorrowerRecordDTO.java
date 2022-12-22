package com.abakli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowerRecordDTO {
    private Long id;
    private BookDTO book;
    private StudentDTO student;
    private StaffDTO staff;
    private Integer numberOfCopies;
    private LocalDate releaseDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
}