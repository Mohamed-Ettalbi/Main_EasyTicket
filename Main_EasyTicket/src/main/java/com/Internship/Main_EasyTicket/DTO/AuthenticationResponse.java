package com.Internship.Main_EasyTicket.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
        private String token;
        private Object user;  // Can hold either TechnicianDTOResponse or UserDTO


    }


