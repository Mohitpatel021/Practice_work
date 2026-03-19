package com.employee.demo.repos;

import com.employee.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Optional<Employee> findByEmpEmail(String email);

    void deleteByEmpEmail(String email);
}
