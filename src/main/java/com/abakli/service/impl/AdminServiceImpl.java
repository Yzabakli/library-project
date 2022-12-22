package com.abakli.service.impl;

import com.abakli.dto.AdminDTO;
import com.abakli.entity.Admin;
import com.abakli.entity.Role;
import com.abakli.mapper.MapperUtil;
import com.abakli.repository.AdminRepository;
import com.abakli.service.AdminService;
import com.abakli.service.RoleService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final MapperUtil mapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public AdminServiceImpl(AdminRepository adminRepository, MapperUtil mapper, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.adminRepository = adminRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public List<AdminDTO> readAll() {
        return adminRepository.findAll()
                .stream()
                .map(admin -> mapper.convert(admin, new AdminDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(AdminDTO dto) {

        Admin convert = mapper.convert(dto, Admin.class);

        convert.setPassword(passwordEncoder.encode(dto.getPassword()));

        convert.setRole(mapper.convert(roleService.findById(1L), new Role()));

        adminRepository.save(convert);
    }

    @Override
    public AdminDTO findByContact(String contact) {
        return mapper.convert(adminRepository.findByContact(contact).orElseThrow(), new AdminDTO());
    }

    @Override
    public void update(AdminDTO dto) {

        Admin admin = adminRepository.findByContact(dto.getContact()).orElseThrow();

        Admin convert = mapper.convert(dto, new Admin());

        convert.setId(admin.getId());

        convert.setPassword(passwordEncoder.encode(dto.getPassword()));

        convert.setRole(mapper.convert(roleService.findById(1L), new Role()));

        adminRepository.save(convert);

    }

    @Override
    public void delete(String contact) {

        Admin admin = adminRepository.findByContact(contact).orElseThrow();

        admin.setIsDeleted(true);

        adminRepository.save(admin);
    }

    @Override
    public Integer countAll() {
        return adminRepository.countByIsDeletedFalse();
    }

    @Override
    public AdminDTO getCurrentUser() {
        return mapper.convert(adminRepository.findByContact(SecurityContextHolder.getContext()
                .getAuthentication()
                .getName()), AdminDTO.class);
    }

    @Override
    public boolean existsByContact(String contact) {

        return adminRepository.existsByContact(contact);
    }
}
