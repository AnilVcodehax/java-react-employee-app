package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First Name required")
    private String firstname;

    @NotBlank(message = "Last Name required")
    private String lastname;

    @Email(message = "Not a valid email format Anil")
    @NotBlank(message = "Email is required")
    private String email;

    // REQUIRED: Default constructor
    public Employee() {}

    // OPTIONAL constructor
    public Employee(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    // ✅ ADD THIS (IMPORTANT)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // <-- MISSING IN MANY CASES
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}