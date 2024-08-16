package com.Internship.Main_EasyTicket.DTO.Mapper;

import com.Internship.Main_EasyTicket.DTO.GroupDTO;
import com.Internship.Main_EasyTicket.DTO.Response.TechnicianDTOResponse;
import com.Internship.Main_EasyTicket.model.Group;
import lombok.Data;

import java.util.List;

@Data
public class GroupMapper {


    public static GroupDTO mapGroupToGorupDTO(Group group){

        List<TechnicianDTOResponse> technicianDTOResponseList = TechnicianListMapper.mapToTechnicianDTORespnse(group.getTechnicians());

        return new GroupDTO(
                group.getId(),
                group.getName(),
                group.getDescription(),
                technicianDTOResponseList
        );




    }

}
