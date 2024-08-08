package com.Internship.Main_EasyTicket.DTO;

public record AddUserRequest(   String name,
                                String last_name,
                                String email,
                                String phone,
                                String password) {
}
