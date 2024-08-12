package com.Internship.Main_EasyTicket.DTO.Mapper;

import com.Internship.Main_EasyTicket.DTO.Response.TechnicianDTOResponse;
import com.Internship.Main_EasyTicket.model.Technician;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TechnicianMapper {


    public static List <TechnicianDTOResponse> mapToTechnicianDTORespnse(List<Technician> technicians) {


        return technicians.stream().map(
                technician -> new TechnicianDTOResponse(
                        technician.getId(),
                        technician.getFirstName(),
                        technician.getLastName(),
                        technician.getEmail(),
                        technician.getPhone()
                        ,technician.getIsApproved(),
                        technician.getGroup() != null ? technician.getGroup().getName() : null
                )).collect(Collectors.toList());





    }
}
