package com.Internship.Main_EasyTicket.DTO.Mapper;

import com.Internship.Main_EasyTicket.DTO.UserDTO;
import com.Internship.Main_EasyTicket.DTO.UserDTOResponse;
import com.Internship.Main_EasyTicket.model.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeListMapper {


    public static List <UserDTOResponse> mapToUserDTORespnse(List<Employee> employees) {


        return employees.stream().map(
                employee -> new UserDTOResponse(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail(),
                        employee.getPhone(),
                        null,

                        employee.getIsApproved()
                        , String.valueOf(employee.getRoles())
                )).collect(Collectors.toList());






    }
}
