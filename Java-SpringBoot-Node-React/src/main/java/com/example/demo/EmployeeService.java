package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public EmployeeDTO create(EmployeeDTO dto) {
        Employee emp = convertToEntity(dto);
        Employee saved = repo.save(emp);
        return convertToDTO(saved);
    }

    // GET ALL
    public List<EmployeeDTO> getAll(int page, int size, String sortBy, String direction) {
        
        Sort sort = direction.equalsIgnoreCase("desc") ?
            Sort.by(sortBy).descending():
            Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page,size,sort);

        Page<Employee> employeePage = repo.findAll(pageable);
        
        return employeePage
                .getContent()
                .stream()
                .map(this::convertToDTO)
                .toList();

        
    }

    // GET BY ID
    public EmployeeDTO getById(Long id) {
        Employee emp = repo.findById(id).orElse(null);
        return mapToDTO(emp);
    }

    // UPDATE
    public EmployeeDTO update(Long id, EmployeeDTO dto) {
        Employee existing = repo.findById(id).orElse(null);

        if (existing != null) {
            existing.setFirstname(dto.getFirstname());
            existing.setLastname(dto.getLastname());
            existing.setEmail(dto.getEmail());

            Employee updated = repo.save(existing);
            return mapToDTO(updated);
        }

        return null;
    }

    // DELETE
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // ENTITY → DTO
    private EmployeeDTO mapToDTO(Employee emp) {
        if (emp == null) return null;

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(emp.getId());
        dto.setFirstname(emp.getFirstname());
        dto.setLastname(emp.getLastname());
        dto.setEmail(emp.getEmail());

        return dto;
    }

    // DTO → ENTITY
    private Employee mapToEntity(EmployeeDTO dto) {
        Employee emp = new Employee();
        emp.setId(dto.getId());
        emp.setFirstname(dto.getFirstname());
        emp.setLastname(dto.getLastname());
        emp.setEmail(dto.getEmail());

        return emp;
    }

    private EmployeeDTO convertToDTO(Employee emp) {
        EmployeeDTO dto = new EmployeeDTO();
        
    
        dto.setId(emp.getId());
        dto.setFirstname(emp.getFirstname());
        dto.setLastname(emp.getLastname());
        dto.setEmail(emp.getEmail());
        
        return dto;
    }

    private Employee convertToEntity(EmployeeDTO dto) {
        Employee emp = new Employee();

        emp.setId(dto.getId());
        emp.setFirstname(dto.getFirstname());
        emp.setLastname(dto.getLastname());
        emp.setEmail(dto.getEmail());

        return emp;
}


    

}