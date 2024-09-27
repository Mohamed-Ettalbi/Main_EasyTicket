package com.Internship.Main_EasyTicket.DTO.Mapper;

import com.Internship.Main_EasyTicket.DTO.Response.TechnicianDTOResponse;
import com.Internship.Main_EasyTicket.model.Technician;

public class TechnicianMapper {


    public static TechnicianDTOResponse mapToTechnicianDTORespnse(Technician  technician) {


        return  new TechnicianDTOResponse(
                        technician.getId(),
                        technician.getFirstName(),
                        technician.getLastName(),
                        technician.getEmail(),
                        technician.getPhone(),
                        technician.getIsApproved(),
                        technician.getGroup() != null ? technician.getGroup().getId() : null,
                        String.valueOf(technician.getRoles())


        );




    }
}
