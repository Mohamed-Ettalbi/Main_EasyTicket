package com.Internship.Main_EasyTicket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Technician extends User{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="groupId")
    @JsonBackReference
    private Group group;



//
//    public Technician(Long id, String firstName, String lastName, String email, String phone, String password, LocalDateTime createdAt, LocalDateTime updatedAt , Long groupId) {
//        super(id,firstName, lastName, email, phone, password, createdAt, updatedAt);
//
//    }



}
