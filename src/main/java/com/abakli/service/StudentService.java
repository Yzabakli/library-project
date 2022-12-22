package com.abakli.service;

import com.abakli.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> readAll();

    void save(StudentDTO studentDTO);

    StudentDTO findByContact(String contact);

    StudentDTO getCurrentUser();

    void update(StudentDTO studentDTO);

    void delete(String contact);

    boolean existsByContact(String contact);
}
