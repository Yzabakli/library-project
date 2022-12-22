package com.abakli.service;

import com.abakli.dto.AdminDTO;
import com.abakli.dto.StaffDTO;

import java.util.List;

public interface StaffService {
    List<StaffDTO> readAll();

    void save(StaffDTO dto);

    StaffDTO findByContact(String contact);

    void update(StaffDTO dto);

    void delete(String contact);

    StaffDTO getCurrentUser();

    boolean existsByContact(String contact);
}
