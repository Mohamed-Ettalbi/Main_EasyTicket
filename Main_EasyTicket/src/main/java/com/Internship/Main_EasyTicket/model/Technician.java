package com.Internship.Main_EasyTicket.model;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class Technician extends User{

    private Long groupId;

    public Technician() {
    }

    public Technician(Long id, String firstName, String lastName, String email, String phone, String password, LocalDateTime createdAt, LocalDateTime updatedAt , Long groupId) {
        super(id,firstName, lastName, email, phone, password, createdAt, updatedAt);
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

}
