package com.Internship.Main_EasyTicket.controller;

import com.Internship.Main_EasyTicket.DTO.Request.UserDTORequest;
import com.Internship.Main_EasyTicket.DTO.Response.TechnicianDTOResponse;
import com.Internship.Main_EasyTicket.DTO.Response.UserDTOResponse;
import com.Internship.Main_EasyTicket.Service.EmployeeService;
import com.Internship.Main_EasyTicket.model.Employee;
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
    public ResponseEntity<UserDTOResponse> addEmployee(@RequestBody @Valid UserDTORequest request) {
      UserDTOResponse  employeeResponse = employeeService.createEmployee(request);
      return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<UserDTOResponse>> getAllEmployee(){

        List<UserDTOResponse> employeeList = employeeService.getAllEmployee();
        return new ResponseEntity<>(employeeList,HttpStatus.OK);



    }

}
