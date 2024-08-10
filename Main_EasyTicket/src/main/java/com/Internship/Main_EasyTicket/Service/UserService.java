package com.Internship.Main_EasyTicket.Service;

import com.Internship.Main_EasyTicket.DTO.UserDTO;
import com.Internship.Main_EasyTicket.DTO.UserRepository;
import com.Internship.Main_EasyTicket.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private  final UserRepository userRepository;




    public List<User> getAllUsers(){

        List<User> users = userRepository.findAll();
        return users;
    }

    public Optional<User> getUserById(Long id) {

        return userRepository.findById(id);
    }

    public User createUser(UserDTO request) {
    User user = new User( );
    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());
    user.setPassword(request.getPassword());
    user.setEmail(request.getEmail());
    user.setPhone(request.getPhone());

    LocalDateTime now = LocalDateTime.now();

    user.setCreatedAt(now);
    user.setUpdatedAt(now);

            return userRepository.save(user);
    }
    public Optional<User> updateUser(Long id, UserDTO request)
    {

        Optional<User> existingUser = userRepository.findById(id);
        if(existingUser.isPresent()) {

            User user = existingUser.get();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        LocalDateTime now = LocalDateTime.now();
        user.setUpdatedAt(now);
            userRepository.save(user);
        return Optional.of(user);
        }else {
            return Optional.empty();
        }

    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);


    }

}
