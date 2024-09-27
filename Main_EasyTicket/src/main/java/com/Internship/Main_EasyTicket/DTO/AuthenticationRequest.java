package com.Internship.Main_EasyTicket.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

        @NotBlank
        private String username;
        @NotBlank
        private String password;

        // Getters and setters


}
