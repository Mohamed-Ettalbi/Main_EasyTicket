package com.Internship.Main_EasyTicket.DTO.Mapper;

import com.Internship.Main_EasyTicket.DTO.Response.TechnicianDTOResponse;
import com.Internship.Main_EasyTicket.model.Technician;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TechnicianListMapper {


    public static List <TechnicianDTOResponse> mapToTechnicianDTORespnse(List<Technician> technicians) {

        if (technicians == null) {
            return new ArrayList<>(); // Return an empty list if technicians is null
        }
        return technicians.stream().map(
                technician -> new TechnicianDTOResponse(
                        technician.getId(),
                        technician.getFirstName(),
                        technician.getLastName(),
                        technician.getEmail(),
                        technician.getPhone()
                        ,technician.getIsApproved(),
                        technician.getGroup() != null ? technician.getGroup().getId() : null,
                        String.valueOf(technician.getRoles())

                )).collect(Collectors.toList());





    }
}
