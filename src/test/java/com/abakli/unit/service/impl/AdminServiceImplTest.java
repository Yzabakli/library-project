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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                setPassword("$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK");
                setRole(new Role() {{
                    setId(1L);
                    setDescription("Admin");
                }});
                setIsDeleted(false);
            }});
            add(new Admin());
            add(new Admin());
        }};

        List<AdminDTO> adminDTOs = new ArrayList<>() {{
            add(new AdminDTO() {{
                setId(1L);
                setFirstName("admin");
                setLastName("user");
                setContact("admin@admin.com");
                setPassword("$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK");
                setRole(new RoleDTO(1L, "Admin"));
            }});
            add(new AdminDTO());
            add(new AdminDTO());
        }};

        when(adminRepository.findAll()).thenReturn(admins);
        when(mapperUtil.convert(any(Admin.class), any(AdminDTO.class))).thenReturn(adminDTOs.get(0), adminDTOs.get(1), adminDTOs.get(2));

        List<AdminDTO> list = adminService.readAll();

        verify(mapperUtil, atLeast(3)).convert(any(Admin.class), any(AdminDTO.class));

        assertEquals(list.size(), 3);

        AdminDTO adminDTO = list.get(0);

        assertEquals(adminDTO.getId(), 1L);
        assertEquals(adminDTO.getFirstName(), "admin");
        assertEquals(adminDTO.getLastName(), "user");
        assertEquals(adminDTO.getContact(), "admin@admin.com");
        assertEquals(adminDTO.getPassword(), "$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK");
        assertEquals(adminDTO.getRole().getId(), 1L);
        assertEquals(adminDTO.getRole().getDescription(), "Admin");
    }

    @Test
    void readAll_empty_list() {

        List<Admin> admins = new ArrayList<>();

        when(adminRepository.findAll()).thenReturn(admins);

        List<AdminDTO> list = adminService.readAll();

        assertTrue(list.isEmpty());
    }

    @Test
    void save() {

        AdminDTO adminDTO = new AdminDTO() {{
            setFirstName("admin");
            setLastName("user");
            setContact("admin@admin.com");
            setPassword("Abc1");
        }};

        Admin admin = new Admin() {{
            setFirstName("admin");
            setLastName("user");
            setContact("admin@admin.com");
            setPassword("Abc1");
        }};

        when(mapperUtil.convert(eq(adminDTO), eq(Admin.class))).thenReturn(admin);
        when(passwordEncoder.encode(anyString())).thenReturn("$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK");
        when(mapperUtil.convert(any(), any(Role.class))).thenReturn(new Role("Admin"));

        adminService.save(adminDTO);

        assertEquals(admin.getPassword(), "$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK");
        assertFalse(admin.getIsDeleted());
        assertEquals(admin.getRole().getDescription(), "Admin");

    }

    @Test
    void findByContact() {

        AdminDTO dto = new AdminDTO() {{
            setFirstName("admin");
            setLastName("user");
            setContact("admin@admin.com");
            setPassword("$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK");
            setRole(new RoleDTO(1L, "Admin"));
        }};

        Admin admin = new Admin() {{
            setId(1L);
            setFirstName("admin");
            setLastName("user");
            setContact("admin@admin.com");
            setPassword("$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK");
            setRole(new Role() {{
                setId(1L);
                setDescription("Admin");
            }});
            setIsDeleted(false);
        }};

        when(adminRepository.findByContact(anyString())).thenReturn(Optional.of(admin));
        when(mapperUtil.convert(eq(admin), any(AdminDTO.class))).thenReturn(dto);

        AdminDTO result = adminService.findByContact("admin@admin.com");

        assertEquals(result.getContact(), admin.getContact());
    }

    @Test
    void update() {

        AdminDTO adminDTO = new AdminDTO() {{
            setFirstName("admin");
            setLastName("mike");
            setContact("admin@admin.com");
            setPassword("Abc1");
        }};

        Admin admin = new Admin() {{
            setId(1L);
            setFirstName("admin");
            setLastName("user");
            setContact("admin@admin.com");
            setPassword("$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK");
            setIsDeleted(false);
            setRole(new Role("Admin"));
            setInsertUserId(1L);
            setLastUpdateUserId(1L);
            setInsertDateTime(LocalDateTime.now());
            setLastUpdateDateTime(LocalDateTime.now());
        }};

        Admin convert = new Admin() {{
            setFirstName("admin");
            setLastName("mike");
            setContact("admin@admin.com");
            setPassword("Abc1");
        }};

        when(adminRepository.findByContact(anyString())).thenReturn(Optional.of(admin));
        when(mapperUtil.convert(eq(adminDTO), any(Admin.class))).thenReturn(convert);
        when(passwordEncoder.encode(anyString())).thenReturn("$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK");
        when(mapperUtil.convert(any(), any(Role.class))).thenReturn(new Role("Admin"));

        adminService.update(adminDTO);

        assertEquals(admin.getId(), convert.getId());
        assertEquals(convert.getLastName(), "mike");
        assertEquals(convert.getPassword(), "$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK");
        assertFalse(convert.getIsDeleted());
        assertEquals(convert.getRole().getDescription(), "Admin");
    }

    @Test
    void delete() {

        Admin admin = new Admin() {{
            setId(1L);
            setFirstName("admin");
            setLastName("user");
            setContact("admin@admin.com");
            setPassword("$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK");
            setRole(new Role() {{
                setId(1L);
                setDescription("Admin");
            }});
            setIsDeleted(false);
        }};

        when(adminRepository.findByContact(anyString())).thenReturn(Optional.of(admin));

        adminService.delete("admin@admin.com");

        assertTrue(admin.getIsDeleted());
    }
}