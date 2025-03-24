package com.lcwsk.controller;

import com.lcwsk.enities.Employee;
import com.lcwsk.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/add")
    public Employee saveEmployee(@RequestBody Employee employee){
        return service.addEmployee(employee);
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployee(){
        return service.getAllEmployee();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable("id") Long emp_id){
        return service.getEmployeeById(emp_id);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id,@RequestBody Employee employee){
        return service.updateEmployee(id,employee);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id){
        service.deleteEmployee(id);
        return "Employee Deleted successfully...";
    }
}
