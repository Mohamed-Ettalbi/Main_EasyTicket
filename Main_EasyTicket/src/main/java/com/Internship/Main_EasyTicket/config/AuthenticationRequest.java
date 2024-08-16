package com.Internship.Main_EasyTicket.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

        private String username;
        private String password;

        // Getters and setters

}
