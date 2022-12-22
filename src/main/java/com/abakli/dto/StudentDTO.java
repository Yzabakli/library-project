package com.abakli.dto;

import com.abakli.enums.Course;
import com.abakli.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String contact;
    @NotBlank
    private String password;
    private RoleDTO role;
    @NotNull
    private Course course;
    @Min(2000)
    @NotNull
    private Integer year;
    private Integer age;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    @NotNull
    private Gender gender;

    public Integer findAge() {

        return LocalDate.now().getYear() - this.birthdate.getYear();
    }
}