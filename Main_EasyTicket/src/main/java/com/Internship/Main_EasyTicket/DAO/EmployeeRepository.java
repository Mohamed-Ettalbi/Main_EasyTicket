package com.Internship.Main_EasyTicket.DAO;

import com.Internship.Main_EasyTicket.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

Boolean existsByEmailIgnoreCase(String email);

}