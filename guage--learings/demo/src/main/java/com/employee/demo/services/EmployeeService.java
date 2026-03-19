package com.employee.demo.services;

import com.employee.demo.model.Employee;
import com.employee.demo.records.EmployeeRequest;
import com.employee.demo.repos.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(EmployeeRequest request){
        Employee employee=null;
        Optional<Employee> optEmployee=employeeRepository.findByEmpEmail(!request.email().isEmpty()?request.email():"");
        if(optEmployee.isPresent()){
           throw new RuntimeException("Employee already exist with this email");
        }
        employee=new Employee();
        employee.setEmp_name(request.name());
        employee.setEmpEmail(request.email());
        employee.setEmp_phone_number(request.phoneNumber());
        employee.setEmp_dept(request.department());
        employee.setEmp_experience(request.experience());
        employee.setEmp_salary(request.salary());
        employee.setEmp_city(request.city());
        employee=employeeRepository.save(employee);
        return employee;
    }
    public Employee getEmployee(String email){
        Optional<Employee> optEmployee=employeeRepository.findByEmpEmail(!email.isEmpty()?email:"");
        System.out.println("Mil gya data"+email);
        if(optEmployee.isEmpty()){
            throw new RuntimeException("Employee not found with this email");
        }
        return optEmployee.get();
    }
    public void deleteEmployee(String email){
      try {
          employeeRepository.deleteByEmpEmail(email);
      } catch (Exception e) {
          System.out.println("unable to delete the employee");
      }
    }
    public Employee updateEmployee(EmployeeRequest request){
        Employee employee=null;
        Optional<Employee> optEmployee=employeeRepository.findByEmpEmail(!request.email().isEmpty()?request.email():"");
        if(optEmployee.isEmpty()){
            throw new RuntimeException("Employee already exist with this email");
        }
        employee=optEmployee.get();
        if(!request.email().isEmpty()){
            employee.setEmpEmail(request.email());
        }if(!request.city().isEmpty()){
            employee.setEmp_city(request.city());
        }if(!request.name().isEmpty()){
            employee.setEmp_name(request.name());
        }if(!request.phoneNumber().isEmpty()){
            employee.setEmp_phone_number(request.phoneNumber());
        }if(!request.department().isEmpty()){
            employee.setEmp_dept(request.department());
        }if(request.experience()!=null){
            employee.setEmp_experience(request.experience());
        }if(request.salary()!=null){
            employee.setEmp_salary(request.salary());
        }
        employeeRepository.save(employee);
        return employee;
    }
}
