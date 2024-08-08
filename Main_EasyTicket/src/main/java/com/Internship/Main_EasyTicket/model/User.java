package com.Internship.Main_EasyTicket.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String last_name;

    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String password;
    @Column
    private Timestamp created_at;
    @Column
    private Timestamp updated_at;


//    contstructors


    public User(Long id,
                String name,
                String last_name,
                String email,
                String phone,
                String password,
                Timestamp created_at,
                Timestamp updated_at) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public User() {
    }
    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }


}
