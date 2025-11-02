package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PAYSLIP")
public class Payslip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYSLIP_ID")
    private int payslipId;

    @Column(name = "EMP_ID")
    private int empId;

    @Column(name = "MONTH")
    private String month;

    @Column(name = "BASIC_SALARY")
    private double basicSalary;

    @Column(name = "HRA")
    private double hra;

    @Column(name = "ALLOWANCES")
    private double allowances;

    @Column(name = "DEDUCTIONS")
    private double deductions;

    @Column(name = "NET_SALARY")
    private double netSalary;

    @Temporal(TemporalType.DATE)
    @Column(name = "GENERATED_DATE")
    private Date generatedDate;

    // Getters and Setters
    public int getPayslipId() {
        return payslipId;
    }
    public void setPayslipId(int payslipId) {
        this.payslipId = payslipId;
    }
    public int getEmpId() {
        return empId;
    }
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public double getBasicSalary() {
        return basicSalary;
    }
    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }
    public double getHra() {
        return hra;
    }
    public void setHra(double hra) {
        this.hra = hra;
    }
    public double getAllowances() {
        return allowances;
    }
    public void setAllowances(double allowances) {
        this.allowances = allowances;
    }
    public double getDeductions() {
        return deductions;
    }
    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }
    public double getNetSalary() {
        return netSalary;
    }
    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }
    public Date getGeneratedDate() {
        return generatedDate;
    }
    public void setGeneratedDate(Date generatedDate) {
        this.generatedDate = generatedDate;
    }
}
