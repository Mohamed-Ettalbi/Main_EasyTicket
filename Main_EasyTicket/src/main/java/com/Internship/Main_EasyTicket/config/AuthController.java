package com.Internship.Main_EasyTicket.config;


import com.Internship.Main_EasyTicket.DAO.UserRepository;
import com.Internship.Main_EasyTicket.DTO.UserDTO;
import com.Internship.Main_EasyTicket.Exceptions.UserNotFoundException;
import com.Internship.Main_EasyTicket.model.Role;
import com.Internship.Main_EasyTicket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Internship.Main_EasyTicket.Exceptions.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder    passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

        // Log to verify the PasswordEncoder bean
        System.out.println("Injected PasswordEncoder: " + passwordEncoder.getClass().getName());
    }



    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user= userRepository.findByEmail(request.getUsername()) .orElseThrow(() -> new UserNotFoundException("there is no user with that surname"));



        String jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO request) {

//        var user= User.builder()
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .phone(request.getPhone())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .roles(Set.of(Role.ROLE_TECHNICIAN))
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
//                .build();
//
//        userRepository.save(user);
//
//        String jwtToken = jwtService.generateToken(user);
        String jwtToken = authService.register(request);

        return ResponseEntity.ok(jwtToken);


    }
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody @Valid UserDTO request) {
//
//        boolean emailExists = userRepository.existsByEmailIgnoreCase(request.getEmail());
//        if (emailExists) {
//            throw new DuplicateEmailException("The email " + request.getEmail() + " is already in use.");
//        }
//
//        Role selectedRole;
//        if ("TECHNICIAN".equalsIgnoreCase(request.getRole())) {
//            selectedRole = Role.ROLE_TECHNICIAN;
//        } else if ("EMPLOYEE".equalsIgnoreCase(request.getRole())) {
//            selectedRole = Role.ROLE_EMPLOYEE;
//        } else {
//            throw new IllegalArgumentException("Invalid role selected");
//        }
//
//        User user = User.builder()
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .phone(request.getPhone())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .roles(Set.of(selectedRole))
//                .isApproved(false) // Mark as not approved initially
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
//                .build();
//
//        userRepository.save(user);
//
//        String jwtToken = jwtService.generateToken(user);
//        return ResponseEntity.ok(jwtToken);
//    }



}
