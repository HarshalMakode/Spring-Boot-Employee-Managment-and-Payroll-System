package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Payslip;
import java.util.List;


public interface PayslipRepository extends JpaRepository<Payslip, Integer> {
    List<Payslip> findByEmpId(int empId);
}
