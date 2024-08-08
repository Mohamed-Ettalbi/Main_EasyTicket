package com.Internship.Main_EasyTicket.Service;

import com.Internship.Main_EasyTicket.DTO.AddUserRequest;
import com.Internship.Main_EasyTicket.DTO.UserRepository;
import com.Internship.Main_EasyTicket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;




    public List<User> getAllUsers(){

        List<User> users = userRepository.findAll();
        return users;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(AddUserRequest request) {
    User user = new User();
    user.setName(request.name());
    user.setLast_name(request.last_name());
    user.setPassword(request.password());
    user.setEmail(request.email());
    user.setPhone(request.phone());

    Timestamp now = new Timestamp(System.currentTimeMillis());

    user.setCreated_at(now);
    user.setUpdated_at(now);

            return userRepository.save(user);
    }
    public User updateUser(Long id, AddUserRequest request)
    {
        User user = userRepository.findById(id).get();
        user.setName(request.name());
        user.setLast_name(request.last_name());
        user.setPassword(request.password());
        user.setEmail(request.email());
        user.setPhone(request.phone());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        user.setUpdated_at(now);
        return userRepository.save(user);

    }
    public void deleteCustomer(Long id){
        userRepository.deleteById(id);


    }

}
