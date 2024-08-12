package com.Internship.Main_EasyTicket.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name="user")
@Inheritance(strategy = InheritanceType.JOINED) // Specify the inheritance strategy
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "firstName")
    private String firstName;

    @Column(nullable = false, name = "lastName")
    private String lastName;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "phone")
    private String phone;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "createdAt")
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "updatedAt")
    private LocalDateTime  updatedAt;

    @Column(name="isApproved")
    @Builder.Default
    private Boolean isApproved =false;

    public User(Long id, String firstName, String lastName, String email, String phone, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }




    //    contstructors




}
