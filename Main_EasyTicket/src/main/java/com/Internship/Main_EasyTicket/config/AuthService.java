package com.Internship.Main_EasyTicket.config;

import com.Internship.Main_EasyTicket.DAO.EmployeeRepository;
import com.Internship.Main_EasyTicket.DAO.TechnicianRepository;
import com.Internship.Main_EasyTicket.DAO.UserRepository;
import com.Internship.Main_EasyTicket.Exceptions.DuplicateEmailException;
import com.Internship.Main_EasyTicket.model.Employee;
import com.Internship.Main_EasyTicket.model.Role;
import com.Internship.Main_EasyTicket.model.Technician;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Internship.Main_EasyTicket.DTO.UserDTO;


import java.time.LocalDateTime;
import java.util.Set;

@Service
public class AuthService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TechnicianRepository technicianRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    public String register(UserDTO request){
        boolean emailExists = userRepository.existsByEmailIgnoreCase(request.getEmail());
        if (emailExists) {
            throw new DuplicateEmailException("The email " + request.getEmail() + " is already in use.");
        }

        Role selectedRole;
        if ("TECHNICIAN".equalsIgnoreCase(request.getRole())) {
            selectedRole = Role.ROLE_TECHNICIAN;
            Technician technician = Technician.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .phone(request.getPhone())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .roles(Set.of(selectedRole))
                    .isApproved(false) // Mark as not approved initially
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            technicianRepository.save(technician);
            String jwtToken = jwtService.generateToken(technician);
            return jwtToken;

        } else if ("EMPLOYEE".equalsIgnoreCase(request.getRole())) {
            selectedRole = Role.ROLE_EMPLOYEE;
            Employee employee = Employee.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .phone(request.getPhone())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .roles(Set.of(selectedRole))
                    .isApproved(false) // Mark as not approved initially
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            employeeRepository.save(employee);
            String jwtToken = jwtService.generateToken(employee);
            return jwtToken;

        } else {
            throw new IllegalArgumentException("Invalid Request ");
        }





    }
}
