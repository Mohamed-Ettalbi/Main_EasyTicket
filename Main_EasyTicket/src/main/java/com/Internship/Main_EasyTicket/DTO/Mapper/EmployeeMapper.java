package com.Internship.Main_EasyTicket.DTO.Mapper;

import com.Internship.Main_EasyTicket.DTO.UserDTO;
import com.Internship.Main_EasyTicket.DTO.UserDTOResponse;
import com.Internship.Main_EasyTicket.model.Employee;
import com.Internship.Main_EasyTicket.model.User;

public class EmployeeMapper {


    public static UserDTOResponse mapEmployeeToUserDTORespnse(Employee employee) {


        return  new UserDTOResponse(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail(),
                        employee.getPhone()
                         ,null
                ,employee.getIsApproved()
                , String.valueOf(employee.getRoles())
                );





    }
}
