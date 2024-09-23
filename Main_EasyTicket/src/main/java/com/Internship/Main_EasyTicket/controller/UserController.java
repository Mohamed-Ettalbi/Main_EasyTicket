package com.Internship.Main_EasyTicket.controller;

import com.Internship.Main_EasyTicket.DTO.UserDTO;
import com.Internship.Main_EasyTicket.DTO.UserDTOResponse;
import com.Internship.Main_EasyTicket.dao.UserRepository;
import com.Internship.Main_EasyTicket.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  UserService userService;

//get all users in List format

    @GetMapping("/all")
    public ResponseEntity<List<UserDTOResponse>> getAllUsers() {

        List<UserDTOResponse> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);

    }


    //get one user as requested by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDTOResponse> getUserById(@PathVariable Long id){

        UserDTOResponse user= userService.getUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);

         }




    @PostMapping("/add")
    public ResponseEntity<UserDTOResponse> createUser(@Valid @RequestBody UserDTO request) {

        UserDTOResponse usertmp = userService.createUser(request);

        return new ResponseEntity<>(usertmp, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTOResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO request) {


        UserDTOResponse user =userService.updateUser(id,request);

            return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){

        userService.deleteUser(id);
        return new ResponseEntity<>("The user with the ID : " +id +"has been deleted", HttpStatus.OK);


    }



}
