package com.abakli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO {
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String contact;
    @NotBlank
    private String password;
    private RoleDTO role;
    @NotBlank
    private String contactNumber;
    @NotBlank
    private String address;
    @NotNull
    private com.abakli.enums.Type Type;
}