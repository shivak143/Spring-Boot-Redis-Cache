package com.lcwsk.service;

import com.lcwsk.enities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee addEmployee(Employee employee);
    List<Employee>  getAllEmployee();
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Long id,Employee employee);
    void deleteEmployee(Long id);
}
