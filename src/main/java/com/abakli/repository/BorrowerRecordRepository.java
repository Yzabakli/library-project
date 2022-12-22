package com.abakli.repository;

import com.abakli.entity.BorrowerRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowerRecordRepository extends JpaRepository<BorrowerRecord, Long> {
    List<BorrowerRecord> findByStudent_ContactAndReturnDateNull(String contact);

    List<BorrowerRecord> findByStudent_ContactAndReturnDateNotNullOrderByReleaseDateDesc(String contact);

    List<BorrowerRecord> findByReturnDateNullOrderByStudent_FirstNameAsc();

    List<BorrowerRecord> findByReturnDateNotNullOrderByStudent_FirstNameAsc();

    Optional<BorrowerRecord> findByStudent_ContactAndBook_IdAndReturnDateNull(String contact, Long id);
}