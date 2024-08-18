package com.Internship.Main_EasyTicket.Service;

import com.Internship.Main_EasyTicket.DAO.EmployeeRepository;
import com.Internship.Main_EasyTicket.DAO.TechnicianRepository;
import com.Internship.Main_EasyTicket.DAO.UserRepository;
import com.Internship.Main_EasyTicket.DTO.AuthenticationRequest;
import com.Internship.Main_EasyTicket.Exceptions.DuplicateEmailException;
import com.Internship.Main_EasyTicket.Exceptions.UserNotFoundException;
import com.Internship.Main_EasyTicket.config.JwtService;
import com.Internship.Main_EasyTicket.model.Employee;
import com.Internship.Main_EasyTicket.model.Role;
import com.Internship.Main_EasyTicket.model.Technician;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Internship.Main_EasyTicket.DTO.UserDTO;
import org.springframework.security.authentication.AuthenticationManager;


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
    @Autowired
    private AuthenticationManager authenticationManager;

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
                    .isApproved(false)
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
                    .isApproved(false)
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


    public String login(AuthenticationRequest request){

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user= userRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("there is no user with that surname"));



        String jwtToken = jwtService.generateToken(user);
        return jwtToken;
    }



}
