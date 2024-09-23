package com.Internship.Main_EasyTicket.Service;

import com.Internship.Main_EasyTicket.DTO.UserDTOResponse;
import com.Internship.Main_EasyTicket.dao.EmployeeRepository;
import com.Internship.Main_EasyTicket.dao.TechnicianRepository;
import com.Internship.Main_EasyTicket.dao.UserRepository;
import com.Internship.Main_EasyTicket.DTO.AuthenticationRequest;
import com.Internship.Main_EasyTicket.DTO.AuthenticationResponse;
import com.Internship.Main_EasyTicket.DTO.Mapper.EmployeeMapper;
import com.Internship.Main_EasyTicket.DTO.Mapper.TechnicianMapper;
import com.Internship.Main_EasyTicket.DTO.Mapper.UserMapper;
import com.Internship.Main_EasyTicket.DTO.Response.TechnicianDTOResponse;
import com.Internship.Main_EasyTicket.Exceptions.DuplicateEmailException;
import com.Internship.Main_EasyTicket.Exceptions.UserNotFoundException;
import com.Internship.Main_EasyTicket.config.JwtService;
import com.Internship.Main_EasyTicket.model.Employee;
import com.Internship.Main_EasyTicket.model.Role;
import com.Internship.Main_EasyTicket.model.Technician;
import com.Internship.Main_EasyTicket.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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

    @Transactional
    public AuthenticationResponse register(UserDTO request){
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
            TechnicianDTOResponse response = TechnicianMapper.mapToTechnicianDTORespnse(technician);

            return new AuthenticationResponse(jwtToken,response);
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
            UserDTOResponse response = EmployeeMapper.mapEmployeeToUserDTORespnse(employee);
            return new AuthenticationResponse(jwtToken,response);

        } else {
            throw new IllegalArgumentException("Invalid Request ");
        }
    }


    public AuthenticationResponse login(AuthenticationRequest request) {

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage());}

//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            var user = userRepository.findByEmail(request.getUsername())
                    .orElseThrow(() -> new UserNotFoundException("there is no user with that surname"));

            String jwtToken = jwtService.generateToken(user);

            Object userResponse;

            if (user instanceof Technician) {
                Technician technician = technicianRepository.getReferenceById(user.getId());
                TechnicianDTOResponse response = TechnicianMapper.mapToTechnicianDTORespnse(technician);
                // Convert to TechnicianDTOResponse
                return new AuthenticationResponse(jwtToken, response);

            } else {// this works for both admin and employee
                User tmpUser = userRepository.getReferenceById(user.getId());
                UserDTOResponse response = UserMapper.mapToUserDTORespnse(tmpUser);
                return new AuthenticationResponse(jwtToken, response);
            }


        }


    }




