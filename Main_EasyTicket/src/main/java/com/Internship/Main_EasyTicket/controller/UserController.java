package com.Internship.Main_EasyTicket.controller;



import com.Internship.Main_EasyTicket.DTO.UserDTO;
import com.Internship.Main_EasyTicket.DTO.UserRepository;
import com.Internship.Main_EasyTicket.Service.UserService;
import com.Internship.Main_EasyTicket.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  UserService userService;

//get all users in List format

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    //get one user as requested by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
         Optional<User> user= userService.getUserById(id);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);

    }


    @PostMapping("/add")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO request) {

        User usertmp = userService.createUser(request);
        return new ResponseEntity<>(usertmp, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@Valid @RequestBody UserDTO request) {
        Optional<User> user =userService.updateUser(id,request);
        if (user.isPresent()) {

            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){

        userService.deleteUser(id);
        return new ResponseEntity<>("The user with the ID : " +id +"has been deleted", HttpStatus.OK);


    }


}
