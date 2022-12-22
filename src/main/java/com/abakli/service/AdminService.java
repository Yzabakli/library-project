package com.abakli.service;

import com.abakli.dto.AdminDTO;

import java.util.List;

public interface AdminService {
    List<AdminDTO> readAll();

    void save(AdminDTO dto);

    AdminDTO findByContact(String contact);

    void update(AdminDTO dto);

    void delete(String contact);

    Integer countAll();

    AdminDTO getCurrentUser();

    boolean existsByContact(String contact);
}
