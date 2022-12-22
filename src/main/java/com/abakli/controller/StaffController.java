package com.abakli.controller;

import com.abakli.dto.AdminDTO;
import com.abakli.dto.StaffDTO;
import com.abakli.enums.Type;
import com.abakli.service.AdminService;
import com.abakli.service.StaffService;
import com.abakli.service.StudentService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private final AdminService adminService;
    private final StaffService staffService;
    private final StudentService studentService;

    public StaffController(AdminService adminService, StaffService staffService, StudentService studentService) {
        this.adminService = adminService;
        this.staffService = staffService;
        this.studentService = studentService;
    }

    @GetMapping("/create-admin")
    public String createAdmin(Model model) {

        model.addAttribute(new AdminDTO());
        model.addAttribute(adminService.readAll());
        model.addAttribute("isOnlyAdmin", adminService.countAll() == 1);
        model.addAttribute("currentContact", adminService.getCurrentUser().getContact());

        return "staff/create-admin";
    }

    @PostMapping("/create-admin")
    public String createAdmin(AdminDTO dto) {

        adminService.save(dto);

        return "redirect:/staff/create-admin";
    }

    @GetMapping("/update-admin/{contact}")
    public String updateAdmin(@PathVariable String contact, Model model) {

        model.addAttribute(adminService.findByContact(contact));
        model.addAttribute(adminService.readAll());

        return "staff/update-admin";
    }

    @PostMapping("/update-admin/{id}")
    public String updateAdmin(AdminDTO dto) {

        adminService.update(dto);

        return "redirect:/staff/create-admin";
    }

    @GetMapping("/delete-admin/{contact}")
    public String deleteAdmin(@PathVariable String contact) {

        if (adminService.getCurrentUser().getContact().equals(contact)) {

            adminService.delete(contact);

            return "redirect:/logout";
        }

        adminService.delete(contact);

        return "redirect:/staff/create-admin";
    }

    @GetMapping("/create")
    public String createStaff(Model model) {

        model.addAttribute(new StaffDTO());
        model.addAttribute(staffService.readAll());
        model.addAttribute(Type.values());

        return "staff/create";
    }

    @PostMapping("/create")
    public String createStaff(@Valid StaffDTO dto, BindingResult bindingResult, Model model) {

        boolean isExist = adminService.existsByContact(dto.getContact()) ||
                staffService.existsByContact(dto.getContact()) ||
                studentService.existsByContact(dto.getContact());

        if (bindingResult.hasErrors() || isExist) {

            model.addAttribute(staffService.readAll());
            model.addAttribute(Type.values());

            return "staff/create";

        }
        staffService.save(dto);

        return "redirect:/staff/create";
    }

    @GetMapping("/update/{contact}")
    public String updateStaff(@PathVariable String contact, Model model) {

        model.addAttribute(staffService.findByContact(contact));
        model.addAttribute(staffService.readAll());
        model.addAttribute(Type.values());

        return "staff/update";
    }

    @GetMapping("/update")
    public String updateStaff(Model model) {

        model.addAttribute(staffService.getCurrentUser());
        model.addAttribute(Type.values());

        return "staff/update";
    }

    @PostMapping("/update/{id}")
    public String updateStaff(StaffDTO dto) {

        staffService.update(dto);

        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        if (staffService.existsByContact(name)) {

            return "redirect:/welcome";

        } else return "redirect:/staff/create";
    }

    @GetMapping("/delete/{contact}")
    public String deleteStaff(@PathVariable String contact) {

        staffService.delete(contact);

        return "redirect:/staff/create";
    }
}
