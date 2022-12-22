package com.abakli.service;

import com.abakli.dto.BorrowerRecordDTO;
import com.abakli.entity.BorrowerRecord;

import java.util.List;

public interface BorrowerRecordService {
    void findByUserContactUnreturned(Long bookId);

    List<BorrowerRecordDTO> findAllByUserContactUnreturned();

    List<BorrowerRecordDTO> findAllByUserContactReturned();

    List<BorrowerRecordDTO> findAllUnreturned();
    List<BorrowerRecordDTO> findAllReturned();

    void returnBook(Long id);

    void delete(Long id);
}
