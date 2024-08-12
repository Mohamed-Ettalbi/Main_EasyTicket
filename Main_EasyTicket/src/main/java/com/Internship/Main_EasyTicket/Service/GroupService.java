package com.Internship.Main_EasyTicket.Service;

import com.Internship.Main_EasyTicket.DAO.*;
import com.Internship.Main_EasyTicket.DTO.Mapper.TechnicianMapper;
import com.Internship.Main_EasyTicket.DTO.Request.AddGroupDTORequest;
import com.Internship.Main_EasyTicket.DTO.Response.TechnicianDTOResponse;
import com.Internship.Main_EasyTicket.Exceptions.DuplicateGroupNameException;
import com.Internship.Main_EasyTicket.Exceptions.GroupNotFoundException;
import com.Internship.Main_EasyTicket.Exceptions.TechnicianNotFoundException;
import com.Internship.Main_EasyTicket.model.Group;
import com.Internship.Main_EasyTicket.model.Technician;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupService {

    private final AdminRepository adminRepository;
    private final GroupRepository groupRepository;
    private final TechnicianRepository technicianRepository;

    public GroupService(AdminRepository adminRepository, GroupRepository groupRepository, TechnicianRepository technicianRepository) {
        this.adminRepository = adminRepository;
        this.groupRepository = groupRepository;
        this.technicianRepository = technicianRepository;
    }

    public Group createGroup(AddGroupDTORequest addGroupDTORequest){
        boolean groupExists = groupRepository.existsByNameIgnoreCase(addGroupDTORequest.getGroupName());
        if (groupExists) {
            throw new DuplicateGroupNameException("A group with the name " + addGroupDTORequest.getGroupName() + " already exists.");
        }else{

            Group group = new Group(addGroupDTORequest.getGroupName(), addGroupDTORequest.getGroupDescription());
            group.setCreatedAt(LocalDateTime.now());
            group.setUpdatedAt(LocalDateTime.now());

           return groupRepository.save(group);
        }

    }

    public void deleteGroupById(Long id) {

        if (!groupRepository.existsById(id)) {
            throw new GroupNotFoundException("Group with ID " + id + " not found.");
        } else {

            groupRepository.deleteById(id);


        }
    }

    public Group updateGroupInfo(Long id,AddGroupDTORequest request){

        Group group = groupRepository.findById(id).
                orElseThrow(() -> new GroupNotFoundException
                        ("Group with ID " + id + " not found."));

        if (groupRepository.existsByNameIgnoreCase(request.getGroupName())
                && !group.getName().equalsIgnoreCase(request.getGroupName())) {
            throw new DuplicateGroupNameException("A group with the name "
                    + request.getGroupName() + " already exists.");

        }else {
            group.setName(request.getGroupName());
            group.setDescription(request.getGroupDescription());
            group.setUpdatedAt(LocalDateTime.now());
            return groupRepository.save(group);


        }

    }


        public List<Group> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        if (groups.isEmpty()) {
            throw new GroupNotFoundException("No groups available.");
        }else {
        return groups;
        }
    }
    public Group getGroupById(Long id) {

        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group with ID " + id + " not found."));
        return group;
    }
    public TechnicianDTOResponse addTechnicianToGroup(Long groupid, Long technicianId){

        Group group = groupRepository.findById(groupid)
                .orElseThrow(()->new GroupNotFoundException("Group with ID " + groupid + " not found."));

        Technician technician = technicianRepository.findById(technicianId)
                .orElseThrow(()->new TechnicianNotFoundException("Technician with ID " + technicianId + " not found."));

        technician.setGroup(group);

        group.getTechnicians().add(technician);
        groupRepository.save(group);
        technicianRepository.save(technician);
         return new TechnicianDTOResponse(technician.getId(),
                technician.getFirstName(),
                technician.getLastName(),
                technician.getEmail(),
                technician.getPhone(),technician.getIsApproved(),
                technician.getGroup().getName());


    }

    public List<TechnicianDTOResponse> getAllTechniciansOfGroup(Long groupid) {

        Group group = groupRepository.findById(groupid)
                .orElseThrow(()->new GroupNotFoundException("Group with ID " + groupid + " not found."));

        List<Technician> techniciansList = group.getTechnicians();

        if (techniciansList.isEmpty()) {
            throw new TechnicianNotFoundException("There is no Technician in the Group with The Id:  " + groupid + "  .");
        }else{
            List<TechnicianDTOResponse> technicianDTOResponseList= TechnicianMapper.mapToTechnicianDTORespnse(techniciansList);

            return technicianDTOResponseList;
        }


    }


}