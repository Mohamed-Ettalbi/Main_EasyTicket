package com.Internship.Main_EasyTicket.controller;

import com.Internship.Main_EasyTicket.DTO.UserDTO;
import com.Internship.Main_EasyTicket.DAO.UserRepository;
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
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        List<UserDTO> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);

    }


    //get one user as requested by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){

         UserDTO user= userService.getUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);

         }




    @PostMapping("/add")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO request) {

        UserDTO usertmp = userService.createUser(request);

        return new ResponseEntity<>(usertmp, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO request) {


        UserDTO user =userService.updateUser(id,request);

            return new ResponseEntity<>(user,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){

        userService.deleteUser(id);
        return new ResponseEntity<>("The user with the ID : " +id +"has been deleted", HttpStatus.OK);


    }



}
