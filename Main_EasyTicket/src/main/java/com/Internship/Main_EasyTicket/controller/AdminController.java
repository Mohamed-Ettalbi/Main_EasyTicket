package com.Internship.Main_EasyTicket.controller;

import com.Internship.Main_EasyTicket.DTO.Response.TechnicianDTOResponse;
import com.Internship.Main_EasyTicket.DTO.UserDTO;
import com.Internship.Main_EasyTicket.DTO.UserDTOResponse;
import com.Internship.Main_EasyTicket.dao.UserRepository;
import com.Internship.Main_EasyTicket.Service.AdminService;
import com.Internship.Main_EasyTicket.Service.GroupService;
import com.Internship.Main_EasyTicket.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private final AdminService adminService;
    @Autowired
    private final UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupService groupService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }
    @PutMapping("/approve/{id}")
    public ResponseEntity<UserDTOResponse> approuveUser(@PathVariable Long id) {

        UserDTOResponse user = userService.approveUser(id);

        return new ResponseEntity<>(user, HttpStatus.OK);


    }
    @PutMapping("/disable/{id}")
    public ResponseEntity<UserDTOResponse> disableUser(@PathVariable Long id) {

        UserDTOResponse user = userService.disableUser(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    
    @PutMapping("/groups/{groupId}/add/{technicianId}")
    public ResponseEntity<TechnicianDTOResponse> addTechnician (@PathVariable Long groupId,
                                                                @PathVariable Long technicianId){


        TechnicianDTOResponse technicianAdded = groupService.addTechnicianToGroup(groupId,technicianId);
    return new ResponseEntity<>(technicianAdded,HttpStatus.CREATED);

    }






}
