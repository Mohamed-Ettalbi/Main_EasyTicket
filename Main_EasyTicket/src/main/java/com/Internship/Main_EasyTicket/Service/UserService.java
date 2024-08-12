package com.Internship.Main_EasyTicket.Service;

import com.Internship.Main_EasyTicket.DTO.Mapper.UserListMapper;
import com.Internship.Main_EasyTicket.DTO.Mapper.UserMapper;
import com.Internship.Main_EasyTicket.DTO.Request.UserDTORequest;
import com.Internship.Main_EasyTicket.DTO.Response.UserDTOResponse;
import com.Internship.Main_EasyTicket.DAO.UserRepository;
import com.Internship.Main_EasyTicket.Exceptions.DuplicateEmailException;
import com.Internship.Main_EasyTicket.Exceptions.NoContentException;
import com.Internship.Main_EasyTicket.Exceptions.UserNotFoundException;
import com.Internship.Main_EasyTicket.model.User;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private  final UserRepository userRepository;




    public List<UserDTOResponse> getAllUsers(){


        List<User> users = userRepository.findAll();
//                .stream().map(user->new UserDTOResponse(
//                user.getId(),user.getFirstName(),user.getLastName(),
//                user.getEmail(),user.getPhone(),user.getIsApproved())).collect(Collectors.toList());


        if(users.isEmpty()) {
            throw new NoContentException("there is no User currently in the system");
        }else
        {

            List<UserDTOResponse> userListResponse = UserListMapper.mapToUserDTORespnse(users);
            return userListResponse;

        }


    }

    public UserDTOResponse getUserById(Long id) {


     User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(
              "the User with the ID: "+id +
                      " Does Not Exist") );

     UserDTOResponse userDTOResponse = UserMapper.mapToUserDTORespnse(user);
     return userDTOResponse;


    }





    public UserDTOResponse createUser(UserDTORequest request) {
        boolean emailExists= userRepository.existsByEmailIgnoreCase(request.getEmail());

        if(emailExists){
            throw new DuplicateEmailException("the email  "+request.getEmail()+" is already In Use ");
        }else {

            User user = new User();
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setPassword(request.getPassword());
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());
            LocalDateTime now = LocalDateTime.now();

            user.setCreatedAt(now);
            user.setUpdatedAt(now);

            userRepository.save(user);
            UserDTOResponse userDTOResponse = UserMapper.mapToUserDTORespnse(user);
            return userDTOResponse;
        }
        }
    public UserDTOResponse updateUser(Long id, UserDTORequest request)
    {

        User existingUser = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException( "the User with the ID: "+id +
                " Does Not Exist"));

             boolean emailExists= userRepository.existsByEmailIgnoreCase(request.getEmail());
             boolean emailBelongToExistingUser= existingUser.getEmail().equals(request.getEmail());
            if(emailExists && !emailBelongToExistingUser){


                throw new DuplicateEmailException("the  email  "+request.getEmail()+"   already In Use ");
            }else {



                existingUser.setFirstName(request.getFirstName());
                existingUser.setLastName(request.getLastName());
                existingUser.setPassword(request.getPassword());
                existingUser.setEmail(request.getEmail());
                existingUser.setPhone(request.getPhone());
                LocalDateTime now = LocalDateTime.now();
                existingUser.setUpdatedAt(now);
                userRepository.save(existingUser);

                UserDTOResponse userDTOResponse = UserMapper.mapToUserDTORespnse(existingUser);
                return userDTOResponse;

            }

    }
    public void deleteUser(Long id){

       if (userRepository.findById(id).isPresent()) {
           userRepository.deleteById(id);

       }else{
           throw new UserNotFoundException("the User with the ID: "+id +" Does Not Exist");
       }

    }
    public UserDTOResponse approveUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("the User with the ID: "+id +" Does Not Exist"));

        user.setIsApproved(true);
        userRepository.save(user);
        UserDTOResponse userDTOResponse = UserMapper.mapToUserDTORespnse(user);

        return userDTOResponse;
    }

    public UserDTOResponse disableUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("the User with the ID: "+id +" Does Not Exist"));

        user.setIsApproved(false);
        userRepository.save(user);
        UserDTOResponse userDTOResponse = UserMapper.mapToUserDTORespnse(user);
        return userDTOResponse;
    }

}
