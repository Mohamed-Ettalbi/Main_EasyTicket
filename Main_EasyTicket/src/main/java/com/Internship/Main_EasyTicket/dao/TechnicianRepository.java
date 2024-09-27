package com.Internship.Main_EasyTicket.dao;


import com.Internship.Main_EasyTicket.model.Technician;
import com.Internship.Main_EasyTicket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {
    Boolean existsByEmailIgnoreCase(String email);
    Optional<Technician> findByEmail(String email);


}
