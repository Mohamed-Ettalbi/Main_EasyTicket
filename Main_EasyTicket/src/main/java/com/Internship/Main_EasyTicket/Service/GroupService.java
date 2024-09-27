package com.Internship.Main_EasyTicket.Service;

import com.Internship.Main_EasyTicket.dao.*;
import com.Internship.Main_EasyTicket.DTO.GroupDTO;
import com.Internship.Main_EasyTicket.DTO.Mapper.GroupListMapper;
import com.Internship.Main_EasyTicket.DTO.Mapper.GroupMapper;
import com.Internship.Main_EasyTicket.DTO.Mapper.TechnicianListMapper;
import com.Internship.Main_EasyTicket.DTO.Mapper.TechnicianMapper;
import com.Internship.Main_EasyTicket.DTO.Request.AddGroupDTORequest;
import com.Internship.Main_EasyTicket.DTO.Response.TechnicianDTOResponse;
import com.Internship.Main_EasyTicket.Exceptions.DuplicateGroupNameException;
import com.Internship.Main_EasyTicket.Exceptions.GroupNotFoundException;
import com.Internship.Main_EasyTicket.Exceptions.TechnicianNotFoundException;
import com.Internship.Main_EasyTicket.model.Group;
import com.Internship.Main_EasyTicket.model.Technician;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupService {

    private final AdminRepository adminRepository;
    private final GroupRepository groupRepository;
    private final TechnicianRepository technicianRepository;

    @Autowired
    public GroupService(AdminRepository adminRepository, GroupRepository groupRepository, TechnicianRepository technicianRepository) {
        this.adminRepository = adminRepository;
        this.groupRepository = groupRepository;
        this.technicianRepository = technicianRepository;
    }

    public GroupDTO createGroup(AddGroupDTORequest addGroupDTORequest){
        boolean groupExists = groupRepository.existsByNameIgnoreCase(addGroupDTORequest.getGroupName());
        if (groupExists) {
            throw new DuplicateGroupNameException("A group with the name " + addGroupDTORequest.getGroupName() + " already exists.");
        }else{

//            Group group = new Group(addGroupDTORequest.getGroupName(), addGroupDTORequest.getGroupDescription());
            Group group = GroupMapper.mapGroupDTOToGorupEntity(addGroupDTORequest);
            group.setCreatedAt(LocalDateTime.now());
            group.setUpdatedAt(LocalDateTime.now());

           groupRepository.save(group);
            return GroupMapper.mapGroupToGorupDTO(group);
        }

    }

    public void deleteGroupById(Long id) {

        if (!groupRepository.existsById(id)) {
            throw new GroupNotFoundException("Group with ID " + id + " not found.");
        } else {

            groupRepository.deleteById(id);


        }
    }

    public GroupDTO updateGroupInfo(Long id,AddGroupDTORequest request){

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
           groupRepository.save(group);
           return GroupMapper.mapGroupToGorupDTO(group);




        }

    }


        public List<GroupDTO> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        if (groups.isEmpty()) {
            throw new GroupNotFoundException("No groups available.");
        }else {
        return GroupListMapper.mapGroupListToGorupDTOList(groups);
        }
    }
    public GroupDTO getGroupById(Long id) {

        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group with ID " + id + " not found."));
        GroupDTO groupDTO= GroupMapper.mapGroupToGorupDTO(group);
        System.out.println(groupDTO);
        return groupDTO;
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
         TechnicianDTOResponse response= TechnicianMapper.mapToTechnicianDTORespnse(technician);
         return response;
    }

    public List<TechnicianDTOResponse> getAllTechniciansOfGroup(Long groupid) {

        Group group = groupRepository.findById(groupid)
                .orElseThrow(()->new GroupNotFoundException("Group with ID " + groupid + " not found."));

        List<Technician> techniciansList = group.getTechnicians();

        if (techniciansList.isEmpty()) {
            throw new TechnicianNotFoundException("There is no Technician in the Group with The Id:  " + groupid + "  .");
        }else{
            List<TechnicianDTOResponse> technicianDTOResponseList= TechnicianListMapper.mapToTechnicianDTORespnse(techniciansList);

            return technicianDTOResponseList;
}


    }


}