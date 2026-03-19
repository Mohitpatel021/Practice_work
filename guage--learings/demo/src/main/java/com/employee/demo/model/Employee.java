package com.employee.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name="employees_data")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer emp_id;

    @Column(nullable = false)
    private String emp_name;
    @Column(unique = true,nullable = true)
    private String emp_phone_number;
    @Column(unique = true,nullable = false)
    private String empEmail;
    private String emp_city;
    private String emp_dept;
    private Double emp_salary;
    private Integer emp_experience;

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_phone_number() {
        return emp_phone_number;
    }

    public void setEmp_phone_number(String emp_phone_number) {
        this.emp_phone_number = emp_phone_number;
    }

    public String getEmp_city() {
        return emp_city;
    }

    public void setEmp_city(String emp_city) {
        this.emp_city = emp_city;
    }

    public String getEmp_dept() {
        return emp_dept;
    }

    public void setEmp_dept(String emp_dept) {
        this.emp_dept = emp_dept;
    }

    public Integer getEmp_experience() {
        return emp_experience;
    }

    public void setEmp_experience(Integer emp_experience) {
        this.emp_experience = emp_experience;
    }

    public Double getEmp_salary() {
        return emp_salary;
    }

    public void setEmp_salary(Double emp_salary) {
        this.emp_salary = emp_salary;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }
}
