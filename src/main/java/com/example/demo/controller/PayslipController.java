package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.model.Payslip;
import com.example.demo.service.PayslipService;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/payslips")
public class PayslipController {

    private final PayslipService service;

    public PayslipController(PayslipService service) {
        this.service = service;
    }

    @GetMapping
    public List<Payslip> getAll() {
        return service.getAllPayslips();
    }

    @GetMapping("/employee/{empId}")
    public List<Payslip> getPayslipsForEmployee(@PathVariable int empId) {
        return service.getPayslipsForEmployee(empId);
    }

    @PostMapping
    public Payslip create(@RequestBody Payslip slip) {
        return service.savePayslip(slip);
    }

    @PostMapping("/generate/{empId}/{month}")
    public Payslip generatePayslip(@PathVariable int empId, @PathVariable String month) {
        return service.generatePayslip(empId, month);
    }

}
