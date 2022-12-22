package com.abakli.service.impl;

import com.abakli.entity.Admin;
import com.abakli.entity.Staff;
import com.abakli.entity.Student;
import com.abakli.entity.User;
import com.abakli.entity.common.UserPrincipal;
import com.abakli.repository.AdminRepository;
import com.abakli.repository.StaffRepository;
import com.abakli.repository.StudentRepository;
import com.abakli.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final AdminRepository adminRepository;
    private final StaffRepository staffRepository;
    private final StudentRepository studentRepository;

    public SecurityServiceImpl(AdminRepository adminRepository, StaffRepository staffRepository, StudentRepository studentRepository) {
        this.adminRepository = adminRepository;
        this.staffRepository = staffRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = null;

        Optional<Admin> admin = adminRepository.findByContact(username);
        
        if (admin.isPresent()) {

            user = admin.get();
        }

        Optional<Staff> staff = staffRepository.findByContact(username);

        if (staff.isPresent()) {

            user = staff.get();
        }

        Optional<Student> student = studentRepository.findByContact(username);

        if (student.isPresent()) {

            user = student.get();
        }

        if (user == null) {

            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
    }
}