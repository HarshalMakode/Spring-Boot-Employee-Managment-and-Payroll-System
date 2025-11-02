package com.example.demo.service;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.*;
import com.example.demo.repository.*;

@Service
public class PayslipService {

    @Autowired
    private PayslipRepository repository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Payslip generatePayslip(int empId, String month) {
        Employee emp = employeeRepository.findById(empId).orElse(null);
        if (emp == null) {
            throw new RuntimeException("Employee not found with ID: " + empId);
        }

        double basic = emp.getBasicSalary();
        double hra = basic * 0.20;
        double allowances = basic * 0.10;
        double deductions = basic * 0.05;
        double netSalary = basic + hra + allowances - deductions;

        Payslip payslip = new Payslip();
        payslip.setEmpId(empId);
        payslip.setMonth(month);
        payslip.setBasicSalary(basic);
        payslip.setHra(hra);
        payslip.setAllowances(allowances);
        payslip.setDeductions(deductions);
        payslip.setNetSalary(netSalary);
        payslip.setGeneratedDate(new Date());

        return repository.save(payslip);
    }

    public List<Payslip> getPayslipsForEmployee(int empId) {
        return repository.findByEmpId(empId);
    }

    public List<Payslip> getAllPayslips() {
        return repository.findAll();
    }

    public Payslip savePayslip(Payslip slip) {
        return repository.save(slip);
    }

}
