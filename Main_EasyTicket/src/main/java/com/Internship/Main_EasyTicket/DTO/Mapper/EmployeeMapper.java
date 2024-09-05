package com.Internship.Main_EasyTicket.DTO.Mapper;

import com.Internship.Main_EasyTicket.DTO.UserDTO;
import com.Internship.Main_EasyTicket.model.Employee;
import com.Internship.Main_EasyTicket.model.User;

public class EmployeeMapper {


    public static UserDTO mapEmployeeToUserDTORespnse(Employee employee) {


        return  new UserDTO(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail(),
                        employee.getPhone()
                         ,employee.getPassword()
                        ,employee.getIsApproved()
                , String.valueOf(employee.getRoles())
                );





    }
}
