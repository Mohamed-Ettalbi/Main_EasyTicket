package com.Internship.Main_EasyTicket.DTO;

import com.Internship.Main_EasyTicket.DTO.Response.TechnicianDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
    private Long groupId;
    private String groupName;
    private String groupDescription;
    private List<TechnicianDTOResponse> technicianDTOResponseList;
}
