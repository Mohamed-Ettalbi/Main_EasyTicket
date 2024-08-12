package com.Internship.Main_EasyTicket.DTO.Mapper;

import com.Internship.Main_EasyTicket.DTO.Response.UserDTOResponse;
import com.Internship.Main_EasyTicket.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {


    public static UserDTOResponse mapToUserDTORespnse(User user) {


        return  new UserDTOResponse(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPhone()
                        ,user.getIsApproved()
                );




    }
}
