package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class EmployeeController {
  
    @Autowired
    private EmployeeService service;
  
    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }
  
    @PostMapping("/addEmployee")
    public List<Employee> addEmployees(@RequestBody List<Employee> employees) {
        return service.saveEmployees(employees);
    }
  
    @GetMapping("/Employees")
    public List<Employee> findAllEmployees() {
        return service.getEmployees();
    }
  
    @GetMapping("/EmployeeByEmpId/{id}")
    public Employee findEmployeeByEmpId(@PathVariable int empId) {
        return service.getEmployeeById(empId);
    }
  
      
      @GetMapping("/Employee/{firstName}") 
      public Employee findEmployeeByFirstName(@PathVariable String firstName) { return
      service.getEmployeeByFirstName(firstName); }
       
  
    @PutMapping("/update")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return service.updateEmployee(employee);
    }
  
    @DeleteMapping("/delete/{empid}")
    public String deleteEmployee(@PathVariable int empId) {
        return service.deleteEmployee(empId);
    }
}
