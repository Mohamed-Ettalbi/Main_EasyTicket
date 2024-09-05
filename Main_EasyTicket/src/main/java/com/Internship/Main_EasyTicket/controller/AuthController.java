package com.Internship.Main_EasyTicket.controller;


import com.Internship.Main_EasyTicket.dao.UserRepository;
import com.Internship.Main_EasyTicket.DTO.AuthenticationResponse;
import com.Internship.Main_EasyTicket.DTO.UserDTO;
import com.Internship.Main_EasyTicket.Service.AuthService;
import com.Internship.Main_EasyTicket.DTO.AuthenticationRequest;
import com.Internship.Main_EasyTicket.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }



    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {

        AuthenticationResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDTO request) {

        AuthenticationResponse response = authService.register(request);

        return ResponseEntity.ok(response);


    }



}
