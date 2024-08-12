package com.Internship.Main_EasyTicket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee extends User{

private Long id;

//    public Employee(Long id, String firstName, String lastName, String email, String phone, String password, LocalDateTime createdAt, LocalDateTime updatedAt, Long id1) {
//        super(id, firstName, lastName, email, phone, password, createdAt, updatedAt);
//        this.id = id1;
//    }
//
//    public Employee(Long id) {
//        this.id = id;
//    }
//
//    public Employee() {
//
//    }


}
