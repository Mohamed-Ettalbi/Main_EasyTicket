package com.Internship.Main_EasyTicket.DTO.Mapper;

import com.Internship.Main_EasyTicket.DTO.UserDTO;
import com.Internship.Main_EasyTicket.DTO.UserDTOResponse;
import com.Internship.Main_EasyTicket.model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserListMapper {


    public static List <UserDTOResponse> mapToUserDTORespnse(List<User> users) {


        return users.stream().map(
                user -> new UserDTOResponse(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPhone(),
                        null
                        ,user.getIsApproved()
                        , String.valueOf(user.getRoles())
                )).collect(Collectors.toList());





    }
}
