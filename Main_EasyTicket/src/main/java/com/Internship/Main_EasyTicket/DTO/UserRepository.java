package com.Internship.Main_EasyTicket.DTO;
import com.Internship.Main_EasyTicket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
