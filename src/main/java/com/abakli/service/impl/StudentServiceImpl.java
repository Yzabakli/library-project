package com.abakli.service.impl;

import com.abakli.dto.StudentDTO;
import com.abakli.entity.Student;
import com.abakli.mapper.MapperUtil;
import com.abakli.repository.StudentRepository;
import com.abakli.service.RoleService;
import com.abakli.service.StudentService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final MapperUtil mapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public StudentServiceImpl(StudentRepository studentRepository, MapperUtil mapper, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<StudentDTO> readAll() {
        return studentRepository.findAll()
                .stream()
                .map(student -> mapper.convert(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(StudentDTO studentDTO) {

        studentDTO.setRole(roleService.findById(3L));

        Student student = mapper.convert(studentDTO, Student.class);

        student.setPassword(passwordEncoder.encode(studentDTO.getPassword()));

        studentRepository.save(student);
    }

    @Override
    public StudentDTO findByContact(String contact) {
        return mapper.convert(studentRepository.findByContact(contact), StudentDTO.class);
    }

    @Override
    public StudentDTO getCurrentUser() {
        return mapper.convert(studentRepository.findByContact(SecurityContextHolder.getContext().getAuthentication().getName()), StudentDTO.class);
    }

    @Override
    public void update(StudentDTO studentDTO) {

        studentDTO.setRole(roleService.findById(3L));

        studentDTO.setAge(studentDTO.findAge());

        Student student = studentRepository.findById(studentDTO.getId()).orElseThrow();

        Student convert = mapper.convert(studentDTO, Student.class);

        convert.setId(student.getId());

        convert.setPassword(passwordEncoder.encode(studentDTO.getPassword()));

        studentRepository.save(convert);
    }

    @Override
    public void delete(String contact) {

        Student student = studentRepository.findByContact(contact).orElseThrow();

        student.setIsDeleted(true);

        studentRepository.save(student);
    }

    @Override
    public boolean existsByContact(String contact) {

        return studentRepository.existsByContact(contact);
    }
}
