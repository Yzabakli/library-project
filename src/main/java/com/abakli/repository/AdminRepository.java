package com.abakli.repository;

import com.abakli.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByContact(String contact);
    Integer countByIsDeletedFalse();

    Optional<Admin> findByContact(String contact);

    @Query("select a from Admin a where a.firstName like concat('%', ?1, '%') order by a.firstName")
    List<Admin> findByFirstNameContainsOrderByFirstNameAsc(String firstName);
}