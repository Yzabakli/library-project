package com.abakli.repository;

import com.abakli.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    boolean existsByContact(String contact);
    Optional<Staff> findByContact(String contact);
}