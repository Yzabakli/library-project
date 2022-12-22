package com.abakli.entity;

import com.abakli.enums.Course;
import com.abakli.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
@Where(clause = "is_deleted = false")
public class Student extends User {

    private Course course;
    private Integer year;
    private Integer age;
    @NotNull(message = "Required")
    private LocalDate birthdate;
    private Gender gender;
}