package com.Internship.Main_EasyTicket.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TechniciansGroup")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[A-Za-z0-9 _-]{3,50}$", message = "Group name must be between 3 and 50 characters long and can only contain letters, numbers, spaces, underscores, and hyphens.")
    private String name;

    @Column(nullable = false, length = 3000)  // Adjust length as needed
    @Size(max = 2500)  // Adjust max size according to your requirements
    private String description;


    @Column(nullable = false , name = "createdAt")
    private LocalDateTime createdAt;

    @Column(nullable = false , name = "updatedAt")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "group")
    @JsonManagedReference
    private List<Technician> technicians ;


    public Group(String groupName, String groupDescription) {
        this.name = groupName;
        this.description = groupDescription;
    }

    @PreRemove
    private void preRemove() {
        for (Technician technician : technicians) {
            technician.setGroup(null);
        }
    }
}
