package com.Internship.Main_EasyTicket.model;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class Admin extends User{

    public Admin(Long adminId,Long id, String firstName, String lastName, String email, String phone, String password, LocalDateTime createdAt, LocalDateTime updatedAt ) {
        super(id,firstName, lastName, email, phone, password, createdAt, updatedAt );
    }





    public Admin() {
    }

}
