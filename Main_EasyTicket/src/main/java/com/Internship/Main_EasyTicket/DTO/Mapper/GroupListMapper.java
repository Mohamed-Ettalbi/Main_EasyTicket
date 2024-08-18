package com.Internship.Main_EasyTicket.DTO.Mapper;

import com.Internship.Main_EasyTicket.DTO.GroupDTO;
import com.Internship.Main_EasyTicket.model.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupListMapper {

    public static List<GroupDTO> mapGroupListToGorupDTOList(List<Group> groups){

        return  groups.stream().map(
                group-> new GroupDTO(
                group.getId(),
                group.getName(),
                group.getDescription(),
                 TechnicianListMapper.mapToTechnicianDTORespnse(group.getTechnicians())

                )
        ).collect(Collectors.toList());

    }
}
