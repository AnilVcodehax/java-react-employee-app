package com.example.demo.controller;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/employees") //with AWS
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final EmployeeService service;

    public EmployeeController(EmployeeService service, EmployeeRepository employeeRepository) {
        this.service = service;
        this.employeeRepository = employeeRepository;
    }

    //CREATE
    @PostMapping
    public EmployeeDTO create(@Valid @RequestBody EmployeeDTO dto) {
        return service.create(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    //GET ALL
    @GetMapping
    public Page<Employee> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size
        //@RequestParam(defaultValue = "id") String sortBy
        //@RequestParam(defaultValue = "dsc") String direction
    ) {
        //return service.findAll(page, size, sortBy, direction);
        //return employeeRepository.findAll(PageRequest.of(page,size).withSort(org.springframework.data.domain.Sort.by(sortBy)));
        return employeeRepository.findAll(PageRequest.of(page, size));
    }

    //GET BY ID
    @GetMapping("/{id}")
    public EmployeeDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    //UPDATE
    @PutMapping("/{id}")
    public EmployeeDTO update(@PathVariable Long id, @Valid @RequestBody EmployeeDTO dto) {
        return service.update(id, dto);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Anil-You have Deleted successfully a record";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/profile")
    public String profile() {
        return "User profile data";
    }
    
}