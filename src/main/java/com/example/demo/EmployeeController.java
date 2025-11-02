package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    @PostMapping("/employees")
    public List<Employee> addEmployees(@RequestBody List<Employee> employees) {
        return service.saveEmployees(employees);
    }

    @GetMapping("/employees")
    public List<Employee> findAllEmployees() {
        return service.getEmployees();
    }

    @GetMapping("/employeeById/{empId}")
    public Employee findEmployeeByEmpId(@PathVariable("empId") int empId) {
        return service.getEmployeeById(empId);
    }

    @GetMapping("/employeeByName/{firstName}")
    public Employee findEmployeeByFirstName(@PathVariable("firstName") String firstName) {
        return service.getEmployeeByFirstName(firstName);
    }

    @PutMapping("/employee")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return service.updateEmployee(employee);
    }

    @DeleteMapping("/employee/{empId}")
    public String deleteEmployee(@PathVariable("empId") int empId) {
        return service.deleteEmployee(empId);
    }
}
