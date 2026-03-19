package com.employee.demo.controllers;

import com.employee.demo.model.Employee;
import com.employee.demo.records.EmployeeRequest;
import com.employee.demo.services.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<Employee>saveEmployee(@RequestBody EmployeeRequest employeeRequest){
        return new ResponseEntity<>(employeeService.saveEmployee(employeeRequest), HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<Employee>getEmployee(@RequestParam(name = "email")String email){
        return new ResponseEntity<>(employeeService.getEmployee(email),HttpStatus.OK);
    }
    @DeleteMapping("/")
    public ResponseEntity<?>deleteEmployee(@RequestParam(name="email") String email){
       try{
           employeeService.deleteEmployee(email);
           return new ResponseEntity<>(HttpStatus.OK);
       }catch (Exception e){
           return  new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
       }


    }
    @PatchMapping("/")
    public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeRequest employeeRequest){
        return new ResponseEntity<>(employeeService.updateEmployee(employeeRequest),HttpStatus.OK);
    }

}
