package com.Internship.Main_EasyTicket.controller;

import com.Internship.Main_EasyTicket.DTO.UserDTO;
import com.Internship.Main_EasyTicket.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<UserDTO> addEmployee(@RequestBody @Valid UserDTO request) {
      UserDTO employeeResponse = employeeService.createEmployee(request);
      return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllEmployee(){

        List<UserDTO> employeeList = employeeService.getAllEmployee();
        return new ResponseEntity<>(employeeList,HttpStatus.OK);



    }

}
