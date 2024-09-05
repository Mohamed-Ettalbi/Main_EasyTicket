package com.Internship.Main_EasyTicket.controller;


import com.Internship.Main_EasyTicket.DTO.GroupDTO;
import com.Internship.Main_EasyTicket.DTO.Request.AddGroupDTORequest;
import com.Internship.Main_EasyTicket.dao.AdminRepository;
import com.Internship.Main_EasyTicket.dao.UserRepository;
import com.Internship.Main_EasyTicket.DTO.Response.TechnicianDTOResponse;
import com.Internship.Main_EasyTicket.Service.GroupService;
import com.Internship.Main_EasyTicket.model.Group;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {


    private final GroupService groupService;



    public GroupController(UserRepository userRepository, AdminRepository adminRepository, GroupService groupService) {

        this.groupService = groupService;

    }

    @PostMapping("/add")
    public ResponseEntity<GroupDTO> addGroup(@RequestBody AddGroupDTORequest group) {
        GroupDTO createdGroup = groupService.createGroup(group);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED); // Return the created group
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Group> deleteGroup(@PathVariable Long id) {

        groupService.deleteGroupById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<GroupDTO> updateGroupInfo(@PathVariable Long id,@RequestBody AddGroupDTORequest group)
    {
        GroupDTO UpdateGroup= groupService.updateGroupInfo(id,group);
        return new ResponseEntity<>(UpdateGroup,HttpStatus.OK);
    }
    @GetMapping("/groups")
    public ResponseEntity<List<GroupDTO>> getGeoupAllGroups()
    {
        List<GroupDTO> groups =groupService.getAllGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Long id){

        GroupDTO group = groupService.getGroupById(id);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }
    @GetMapping("/group/{id}/technicians")
    public ResponseEntity<List<TechnicianDTOResponse>> getAllTechniciansOfGroup (@PathVariable Long id){

        List<TechnicianDTOResponse> technicians = groupService.getAllTechniciansOfGroup(id);
        return new ResponseEntity<>(technicians,HttpStatus.OK);


    }
}
