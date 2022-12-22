package com.abakli.controller;

import com.abakli.dto.StudentDTO;
import com.abakli.enums.Course;
import com.abakli.service.AdminService;
import com.abakli.service.StaffService;
import com.abakli.service.StudentService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final StaffService staffService;
    private final AdminService adminService;

    public StudentController(StudentService studentService, StaffService staffService, AdminService adminService) {
        this.studentService = studentService;
        this.staffService = staffService;
        this.adminService = adminService;
    }

    @GetMapping("/create")
    public String createStudent(Model model) {

        model.addAttribute("studentDTO", new StudentDTO());
        model.addAttribute(studentService.readAll());
        model.addAttribute(Course.values());

        return "student/create";
    }

    @GetMapping("/create-profile")
    public String createProfile(Model model) {

        model.addAttribute("studentDTO", new StudentDTO());
        model.addAttribute(Course.values());

        return "student/create";
    }

    @PostMapping("/create")
    public String createStudent(@Valid StudentDTO studentDTO, BindingResult bindingResult, Model model) {

        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        boolean isExist = adminService.existsByContact(studentDTO.getContact()) ||
                staffService.existsByContact(studentDTO.getContact()) ||
                studentService.existsByContact(studentDTO.getContact());

        if (bindingResult.hasErrors() || isExist) {

            model.addAttribute(studentService.readAll());
            model.addAttribute(Course.values());

            return "student/create";

        }
        studentService.save(studentDTO);

        if (staffService.existsByContact(name)) {

            return "redirect:/student/create";

        } else return "redirect:/login";
    }

    @GetMapping("/update/{contact}")
    public String updateStudent(@PathVariable String contact, Model model) {

        model.addAttribute("studentDTO", studentService.findByContact(contact));
        model.addAttribute(studentService.readAll());
        model.addAttribute(Course.values());

        return "student/update";
    }

    @GetMapping("/update-profile")
    public String updateProfile(Model model) {

        model.addAttribute("studentDTO", studentService.getCurrentUser());
        model.addAttribute(Course.values());

        return "student/update";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(StudentDTO studentDTO) {

        studentService.update(studentDTO);

        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        if (staffService.existsByContact(name)) {

            return "redirect:/student/create";

        } else return "redirect:/welcome";
    }

    @GetMapping("/delete/{contact}")
    public String deleteStaff(@PathVariable String contact) {

        studentService.delete(contact);

        return "redirect:/student/create";
    }
}
