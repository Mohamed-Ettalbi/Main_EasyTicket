package com.Internship.Main_EasyTicket.DAO;


import com.Internship.Main_EasyTicket.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {
    Boolean existsByEmailIgnoreCase(String email);


}
