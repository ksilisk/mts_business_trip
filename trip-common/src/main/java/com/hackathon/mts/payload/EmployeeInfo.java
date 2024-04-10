package com.hackathon.mts.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfo {
    private String name;
    private String surname;
    private String patronymic;
    private String phone;
    private String email;
    private String position;
    private String role;
}
