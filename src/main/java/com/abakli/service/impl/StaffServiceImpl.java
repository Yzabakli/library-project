package com.abakli.service.impl;

import com.abakli.dto.StaffDTO;
import com.abakli.entity.Staff;
import com.abakli.mapper.MapperUtil;
import com.abakli.repository.StaffRepository;
import com.abakli.service.RoleService;
import com.abakli.service.StaffService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final MapperUtil mapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public StaffServiceImpl(StaffRepository staffRepository, MapperUtil mapper, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.staffRepository = staffRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public List<StaffDTO> readAll() {
        return staffRepository.findAll()
                .stream()
                .map(staff -> mapper.convert(staff, new StaffDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(StaffDTO dto) {

        dto.setRole(roleService.findById(2L));

        Staff staff = mapper.convert(dto, new Staff());

        staff.setPassword(passwordEncoder.encode(dto.getPassword()));

        staffRepository.save(staff);
    }

    @Override
    public StaffDTO findByContact(String contact) {
        return mapper.convert(staffRepository.findByContact(contact).orElseThrow(), new StaffDTO());
    }

    @Override
    public void update(StaffDTO dto) {

        dto.setRole(roleService.findById(2L));

        Staff staff = staffRepository.findByContact(dto.getContact()).orElseThrow();

        Staff convert = mapper.convert(dto, new Staff());

        convert.setId(staff.getId());

        convert.setPassword(passwordEncoder.encode(dto.getPassword()));

        staffRepository.save(convert);
    }

    @Override
    public void delete(String contact) {

        Staff staff = staffRepository.findByContact(contact).orElseThrow();

        staff.setIsDeleted(true);

        staffRepository.save(staff);
    }

    @Override
    public StaffDTO getCurrentUser() {
        return mapper.convert(staffRepository.findByContact(SecurityContextHolder.getContext().getAuthentication().getName()), StaffDTO.class);
    }

    @Override
    public boolean existsByContact(String contact) {
        return staffRepository.existsByContact(contact);
    }
}
