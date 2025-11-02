package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    public Employee getEmployeeById(int id) {
        return repo.findById(id).orElse(null);
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return repo.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        int id = employee.getEmpId();
        if (repo.existsById(id)) {
            return repo.save(employee);
        } else {
            return null; // Or throw exception if you want
        }
    }


    public void deleteEmployee(Integer id) {
        repo.deleteById(id);
    }
}
