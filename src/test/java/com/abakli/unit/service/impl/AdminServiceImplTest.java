package com.abakli.unit.service.impl;

import com.abakli.dto.AdminDTO;
import com.abakli.dto.RoleDTO;
import com.abakli.entity.Admin;
import com.abakli.entity.Role;
import com.abakli.mapper.MapperUtil;
import com.abakli.repository.AdminRepository;
import com.abakli.service.RoleService;
import com.abakli.service.impl.AdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

    private AdminServiceImpl adminService;
    @Mock
    private AdminRepository adminRepository;
    @Mock
    private MapperUtil mapperUtil;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleService roleService;

    @BeforeEach
    void setUp() {

        adminService = new AdminServiceImpl(adminRepository, mapperUtil, passwordEncoder, roleService);
    }

    @Test
    void readAll() {

        List<Admin> admins = new ArrayList<>() {{
            add(new Admin() {{
                setId(1L);
                setFirstName("admin");
                setLastName("user");
                setContact("admin@admin.com");
                setPassword(passwordEncoder.encode("Abc1"));
                setRole(new Role());
                setIsDeleted(false);
            }});
            add(new Admin());
            add(new Admin());
        }};

//        List<AdminDTO> adminDTOs = new ArrayList<>() {{
//            add(new AdminDTO() {{
//                setId(1L);
//                setFirstName("admin");
//                setLastName("user");
//                setContact("admin@admin.com");
//                setPassword(passwordEncoder.encode("Abc1"));
//                setRole(new RoleDTO());
//            }});
//            add(new AdminDTO());
//            add(new AdminDTO());
//        }};

        when(adminRepository.findAll()).thenReturn(admins);
        when(mapperUtil.convert(any(Admin.class), AdminDTO.class)).thenReturn(new AdminDTO());

        List<AdminDTO> list = adminService.readAll();

        verify(mapperUtil, atLeast(3)).convert(any(Admin.class), AdminDTO.class);

        assertEquals(list.size(), 3);

        AdminDTO adminDTO = list.get(0);

        assertEquals(adminDTO.getId(), 1L);
        assertEquals(adminDTO.getFirstName(), "admin");
        assertEquals(adminDTO.getLastName(), "user");
        assertEquals(adminDTO.getContact(), "admin@admin.com");
        assertEquals(adminDTO.getPassword(), "$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK");
        assertTrue(passwordEncoder.matches("Abc1", "$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK"));
        assertNotNull(adminDTO.getRole());
    }

    @Test
    void save() {
    }

    @Test
    void findByContact() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void countAll() {
    }

    @Test
    void getCurrentUser() {
    }

    @Test
    void existsByContact() {
    }
}