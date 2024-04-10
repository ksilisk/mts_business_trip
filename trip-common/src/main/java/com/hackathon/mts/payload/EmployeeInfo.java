package com.hackathon.mts.tripemployeedirectory.payload;

import com.hackathon.mts.tripemployeedirectory.entity.Employee;
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

    public static EmployeeInfo from(Employee employee) {
        return new EmployeeInfo(employee.getName(), employee.getSurname(), employee.getPatronymic(),
                employee.getPhone(), employee.getEmail(), employee.getPosition(), employee.getRole().getRole());
    }
}
