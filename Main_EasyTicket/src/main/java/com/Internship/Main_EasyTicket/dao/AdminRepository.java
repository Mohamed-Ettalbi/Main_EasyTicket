package com.Internship.Main_EasyTicket.dao;

import com.Internship.Main_EasyTicket.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Employee, Long> {


}