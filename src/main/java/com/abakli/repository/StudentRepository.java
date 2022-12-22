package com.abakli.repository;

import com.abakli.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByContact(String contact);

    Optional<Student> findByContact(String contact);
}