package com.Internship.Main_EasyTicket.dao;

import com.Internship.Main_EasyTicket.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {

     Boolean existsByNameIgnoreCase(String name);

}
