package com.Internship.Main_EasyTicket.Service;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.Internship.Main_EasyTicket.DTO.Request.UserDTORequest;
import com.Internship.Main_EasyTicket.DTO.Response.UserDTOResponse;
import com.Internship.Main_EasyTicket.DAO.UserRepository;
import com.Internship.Main_EasyTicket.Exceptions.DuplicateEmailException;
import com.Internship.Main_EasyTicket.Exceptions.UserNotFoundException;
import com.Internship.Main_EasyTicket.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;


    private UserService underTest;


    @BeforeEach
    void setUp() {
        underTest = new UserService(userRepository);
    }


    @Test
    void canGetAllUsers() {

        //given
        User user1 =  new User(1L,
                "testGet",
                "ById",
                "testGet@email.com",
                "0123456789",
                "hhhhhhhhhhhhhhhh",
                LocalDateTime.of(2024, 8, 10, 6, 50, 10),
                LocalDateTime.of(2024, 8, 10, 6, 50, 10));
        User user2 =  new User(2L,
                "test2",
                "ById2",
                "test2@email.com",
                "1111111111",
                "hhhhhhhhhhhhhhhh",
                LocalDateTime.of(2024, 8, 10, 6, 50, 10),
                LocalDateTime.of(2024, 8, 10, 6, 50, 10));


        when(userRepository.findAll()).thenReturn(List.of(user1,user2));
        //when
       List<UserDTOResponse> result= underTest.getAllUsers();
        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);

        assertThat(result.get(0).getFirstName()).isEqualTo(user1.getFirstName());
        assertThat(result.get(0).getLastName()).isEqualTo(user1.getLastName());

        assertThat(result.get(1).getFirstName()).isEqualTo(user2.getFirstName());
        assertThat(result.get(1).getLastName()).isEqualTo(user2.getLastName());


    }

    @Test
    void canGetUserById() {

        User user = new User(1L,
                "testGet",
                "ById",
                "testGet@email.com",
                "0123456789",
                "hhhhhhhhhhhhhhhh",
                LocalDateTime.of(2024, 8, 10, 6, 50, 10),
                LocalDateTime.of(2024, 8, 10, 6, 50, 10));


        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        //when

        UserDTOResponse newUser = underTest.getUserById(1L);

//    verify(userRepository).findById(user.getId());

        //then

        assertThat(newUser.getId()).isEqualTo(1L);
        assertThat(newUser.getFirstName()).isEqualTo("testGet");
        assertThat(newUser.getLastName()).isEqualTo("ById");
        assertThat(newUser.getEmail()).isEqualTo("testGet@email.com");
        assertThat(newUser.getPhone()).isEqualTo("0123456789");



    }

    @Test
    void canCreateUser() {
        // Given
        UserDTORequest userDTORequest = new UserDTORequest("mohamed",
                "ettalbi",
                "mohamed@ettalbi",
                "lkqjfmlqkdjfmq",
                "0620150455");


        // When
        LocalDateTime before = LocalDateTime.now();
        underTest.createUser(userDTORequest);
        LocalDateTime after = LocalDateTime.now();

        // Then

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(argumentCaptor.capture());

        User capturedUser = argumentCaptor.getValue();

        assertThat(capturedUser.getFirstName()).isEqualTo(userDTORequest.getFirstName());
        assertThat(capturedUser.getLastName()).isEqualTo(userDTORequest.getLastName());
        assertThat(capturedUser.getEmail()).isEqualTo(userDTORequest.getEmail());
        assertThat(capturedUser.getPhone()).isEqualTo(userDTORequest.getPhone());
        assertThat(capturedUser.getPassword()).isEqualTo(userDTORequest.getPassword());

        assertThat(capturedUser.getCreatedAt()).isAfterOrEqualTo(before).isBeforeOrEqualTo(after);
        assertThat(capturedUser.getUpdatedAt()).isAfterOrEqualTo(before).isBeforeOrEqualTo(after);


    }


    @Test
    void CanUpdateUser() {


        //given

        // Existing user To be UPDATED
        User mockedUser = new User(1L,
                "mohamed",
                "ettalbi",
                "mohamed@ettalbi",
                "0620150455",
                "lkqjfmlqkdjfmq",
                LocalDateTime.of(2024, 8, 10, 6, 50, 10),
                LocalDateTime.of(2024, 8, 10, 6, 50, 10));

        // return the existing User
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockedUser));


        //new Values for the existing user
        UserDTORequest userDTORequest = new UserDTORequest("updatedname",
                "updatedlastname",
                "updated@ettalbi",
                "jjjjjj",
                "00000000000");


        //when
        Long mockedUserID = mockedUser.getId();
        underTest.updateUser(mockedUserID, userDTORequest);


        //then


        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(argumentCaptor.capture());

        User capturedUser = argumentCaptor.getValue();

        // Values that should staty equal

        assertThat(capturedUser.getFirstName()).isEqualTo(userDTORequest.getFirstName());
        assertThat(capturedUser.getLastName()).isEqualTo(userDTORequest.getLastName());
        assertThat(capturedUser.getEmail()).isEqualTo(userDTORequest.getEmail());
        assertThat(capturedUser.getPassword()).isEqualTo(userDTORequest.getPassword());
        assertThat(capturedUser.getPhone()).isEqualTo(userDTORequest.getPhone());
        assertThat(capturedUser.getCreatedAt()).isEqualTo(LocalDateTime.of(2024, 8, 10, 6, 50, 10));

        //the value that should be different
        assertThat(capturedUser.getUpdatedAt()).isAfter(LocalDateTime.of(2024, 8, 10, 6, 50, 10));


    }
    @Test
    void canUpdateUserWithHisOldEMail(){
        //given
            // Existing user To be UPDATED
            User mockedUser = new User(1L,
                    "mohamed",
                    "ettalbi",
                    "mohamed@ettalbi",
                    "0620150455",
                    "lkqjfmlqkdjfmq",
                    LocalDateTime.of(2024, 8, 10, 6, 50, 10),
                    LocalDateTime.of(2024, 8, 10, 6, 50, 10));

            // return the existing User
            when(userRepository.findById(1L)).thenReturn(Optional.of(mockedUser));
             when(userRepository.existsByEmailIgnoreCase(anyString())).thenReturn(true);




        //new Values for the existing user
            UserDTORequest userDTORequest = new UserDTORequest("updatedname",
                    "updatedlastname",
                    "mohamed@ettalbi",
                    "jjjjjj",
                    "00000000000");

        //when
        underTest.updateUser(mockedUser.getId(),userDTORequest);

        //then
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(argumentCaptor.capture());

        User capturedUser = argumentCaptor.getValue();

        assertThat(capturedUser.getFirstName()).isEqualTo(userDTORequest.getFirstName());
        assertThat(capturedUser.getLastName()).isEqualTo(userDTORequest.getLastName());
        assertThat(capturedUser.getEmail()).isEqualTo(userDTORequest.getEmail());
        assertThat(capturedUser.getPassword()).isEqualTo(userDTORequest.getPassword());
        assertThat(capturedUser.getPhone()).isEqualTo(userDTORequest.getPhone());
        assertThat(capturedUser.getCreatedAt()).isEqualTo(LocalDateTime.of(2024, 8, 10, 6, 50, 10));

        //the value that should be different
        assertThat(capturedUser.getUpdatedAt()).isAfter(LocalDateTime.of(2024, 8, 10, 6, 50, 10));





    }
    @Test
    void shouldThrowDuplicateEmailExceptionIfEmailBelongsToAnotherUser(){
        //given
        // Existing user To be UPDATED
        User mockedUser = new User(1L,
                "mohamed",
                "ettalbi",
                "mohamed@ettalbi",
                "0620150455",
                "lkqjfmlqkdjfmq",
                LocalDateTime.of(2024, 8, 10, 6, 50, 10),
                LocalDateTime.of(2024, 8, 10, 6, 50, 10));

                //new Values for the existing user
                        UserDTORequest userDTORequest = new UserDTORequest("updatedname",
                 "updatedlastname",
                    "mohamed333@ettalbi",
                    "jjjjjj",
                 "00000000000");



        // return the existing User
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockedUser));
        when(userRepository.existsByEmailIgnoreCase(userDTORequest.getEmail())).thenReturn(true);

        //THE UPDATED EMAIL

        //then
       assertThatThrownBy(()->underTest.updateUser(mockedUser.getId(),userDTORequest))
               .isInstanceOf(DuplicateEmailException.class)
               .hasMessageContaining("the  email  "+userDTORequest.getEmail()+"   already In Use ");



    }

    @Test
    void canThrowDuplicateMailException(){
     //given
        UserDTORequest userDTORequest = new UserDTORequest("updatedname",
                "updatedlastname",
                "updated@ettalbi",
                "jjjjjj",
                "00000000000");


    //when
                        // we make sure that existWithSameMail will return TRUE
        given(userRepository.existsByEmailIgnoreCase(userDTORequest.getEmail())).willReturn(true);

        assertThatThrownBy(()-> underTest.createUser(userDTORequest))
                .isInstanceOf(DuplicateEmailException.class)
                .hasMessageContaining(
                 "the email  "+userDTORequest.getEmail()+" is already In Use ");


                        //we just verify because we know that it will never progress to save student
                        // becaues it will fail the if statements

        verify(userRepository,never()).save(any());





 }

    @Test
    void deleteUser() {


        //given
        User mockedUser = new User(1L,
                "mohamed",
                "ettalbi",
                "mohamed@ettalbi",
                "0620150455",
                "lkqjfmlqkdjfmq",
                LocalDateTime.of(2024, 8, 10, 6, 50, 10),
                LocalDateTime.of(2024, 8, 10, 6, 50, 10));

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockedUser));

        //when
        underTest.deleteUser(mockedUser.getId());
        //then
        verify(userRepository).deleteById(mockedUser.getId());

    }

    @Test
    void canThrowExceptionWhenUserNotFound() {
        // Given
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // When
        // Then
        assertThatThrownBy(()-> underTest.getUserById(id))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(
                        "the User with the ID: "+id +
                                " Does Not Exist");
    }

    @Test
    void canThrowExceptionWhenUserNotFoundForUpdate(){
    //Given
        Long id = 1L;
        UserDTORequest user = new UserDTORequest(
                "mohamed",
                "ettalbi",
                "mohamed@ettalbi",
                "0620150455",
                "lkqjfmlqkdjfmq");
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> underTest.updateUser(id,user)).isInstanceOf(
                UserNotFoundException.class)
                .hasMessageContaining("the User with the ID: "+id +
                        " Does Not Exist");


}

}
