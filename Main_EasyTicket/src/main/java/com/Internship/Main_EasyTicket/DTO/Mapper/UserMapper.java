package com.Internship.Main_EasyTicket.DTO.Mapper;

import com.Internship.Main_EasyTicket.DTO.UserDTO;
import com.Internship.Main_EasyTicket.DTO.UserDTOResponse;
import com.Internship.Main_EasyTicket.model.User;

public class UserMapper {


    public static UserDTOResponse mapToUserDTORespnse(User user) {


        return  new UserDTOResponse(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPhone()
                         ,user.getPassword()
                        ,user.getIsApproved()
                , String.valueOf(user.getRoles())
                );





    }
}
