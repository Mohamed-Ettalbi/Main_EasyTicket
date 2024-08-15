package com.Internship.Main_EasyTicket.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name="user")
@Inheritance(strategy = InheritanceType.JOINED) // Specify the inheritance strategy
public class User implements UserDetails {

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

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return roles.stream().map(
                role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());


    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {

        return email;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;}

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isApproved;
    }


    //    contstructors




}
