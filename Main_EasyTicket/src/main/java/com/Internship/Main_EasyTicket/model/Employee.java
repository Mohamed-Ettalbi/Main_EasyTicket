package com.Internship.Main_EasyTicket.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Employee extends User{



    public Employee(Long id, String firstName, String lastName, String email, String phone, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super( id,firstName, lastName, email, phone, password, createdAt, updatedAt);
    }


    public Employee() {

    }
}
