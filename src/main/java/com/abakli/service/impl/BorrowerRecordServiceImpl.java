package com.abakli.service.impl;

import com.abakli.dto.BorrowerRecordDTO;
import com.abakli.entity.Book;
import com.abakli.entity.BorrowerRecord;
import com.abakli.entity.Staff;
import com.abakli.entity.Student;
import com.abakli.mapper.MapperUtil;
import com.abakli.repository.BorrowerRecordRepository;
import com.abakli.service.BookService;
import com.abakli.service.BorrowerRecordService;
import com.abakli.service.StaffService;
import com.abakli.service.StudentService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowerRecordServiceImpl implements BorrowerRecordService {

    private final BorrowerRecordRepository repository;
    private final BookService bookService;
    private final StudentService studentService;
    private final StaffService staffService;
    private final MapperUtil mapper;

    public BorrowerRecordServiceImpl(BorrowerRecordRepository repository, @Lazy BookService bookService, StudentService studentService, StaffService staffService, MapperUtil mapper) {
        this.repository = repository;
        this.bookService = bookService;
        this.studentService = studentService;
        this.staffService = staffService;
        this.mapper = mapper;
    }

    @Override
    public void findByUserContactUnreturned(Long bookId) {
        String contact = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<BorrowerRecord> record = repository.findByStudent_ContactAndBook_IdAndReturnDateNull(contact, bookId);

        if (record.isEmpty()) {

            BorrowerRecord borrowerRecord = new BorrowerRecord(mapper.convert(bookService.findById(bookId), Book.class), mapper.convert(studentService.findByContact(contact), Student.class), 1, LocalDate.now(), LocalDate.now()
                    .plusMonths(1));

            repository.save(borrowerRecord);
            return;
        }

        BorrowerRecord borrowerRecord = record.orElseThrow();

        borrowerRecord.setNumberOfCopies(borrowerRecord.getNumberOfCopies() + 1);

        repository.save(borrowerRecord);
    }

    @Override
    public List<BorrowerRecordDTO> findAllByUserContactUnreturned() {

        return repository.findByStudent_ContactAndReturnDateNull(SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName())
                .stream()
                .map(borrowerRecord -> mapper.convert(borrowerRecord, BorrowerRecordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BorrowerRecordDTO> findAllByUserContactReturned() {

        return repository.findByStudent_ContactAndReturnDateNotNullOrderByReleaseDateDesc(SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName())
                .stream()
                .map(borrowerRecord -> mapper.convert(borrowerRecord, BorrowerRecordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BorrowerRecordDTO> findAllUnreturned() {
        return repository.findByReturnDateNullOrderByStudent_FirstNameAsc()
                .stream()
                .map(borrowerRecord -> mapper.convert(borrowerRecord, BorrowerRecordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BorrowerRecordDTO> findAllReturned() {
        return repository.findByReturnDateNotNullOrderByStudent_FirstNameAsc()
                .stream()
                .map(borrowerRecord -> mapper.convert(borrowerRecord, BorrowerRecordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void returnBook(Long id) {

        BorrowerRecord record = repository.findById(id).orElseThrow();

        record.setStaff(mapper.convert(staffService.getCurrentUser(), Staff.class));

        record.setReturnDate(LocalDate.now());

        bookService.returnBook(record.getBook().getId(), record.getNumberOfCopies());

        repository.save(record);
    }

    @Override
    public void delete(Long id) {

        BorrowerRecord record = repository.findById(id).orElseThrow();

        record.setIsDeleted(true);

        repository.save(record);
    }
}
