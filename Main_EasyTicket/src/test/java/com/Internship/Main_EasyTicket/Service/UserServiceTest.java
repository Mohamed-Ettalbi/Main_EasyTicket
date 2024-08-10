package com.Internship.Main_EasyTicket.Service;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.Internship.Main_EasyTicket.DTO.UserDTO;
import com.Internship.Main_EasyTicket.DTO.UserRepository;
import com.Internship.Main_EasyTicket.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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


        //when
        underTest.getAllUsers();
        //then
        verify(userRepository).findAll();
    }

@Test
void canGetUserById() {

User user = new User(1L,
        "testGet",
        "ById",
        "testGet@email.com",
        "0123456789",
        "hhhhhhhhhhhhhhhh",
        LocalDateTime.of(2024,8,10,6,50,10),
        LocalDateTime.of(2024,8,10,6,50,10));


    when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        //when

    Optional<User> newUser= underTest.getUserById(1L);

//    verify(userRepository).findById(user.getId());

    //then

    assertThat(newUser).isPresent();
    assertThat(newUser.get().getId()).isEqualTo(1L);
    assertThat(newUser.get()).isEqualTo(user);
    }

    @Test
    void canCreateUser() {
        // Given
        UserDTO userDTO = new UserDTO("mohamed",
                "ettalbi",
                "mohamed@ettalbi",
                "lkqjfmlqkdjfmq",
                "0620150455");


        // When
        LocalDateTime before = LocalDateTime.now();
        underTest.createUser(userDTO);
        LocalDateTime after = LocalDateTime.now();

        // Then

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(argumentCaptor.capture());

        User capturedUser = argumentCaptor.getValue();

        assertThat(capturedUser.getFirstName()).isEqualTo(userDTO.getFirstName());
        assertThat(capturedUser.getLastName()).isEqualTo(userDTO.getLastName());
        assertThat(capturedUser.getEmail()).isEqualTo(userDTO.getEmail());
        assertThat(capturedUser.getPhone()).isEqualTo(userDTO.getPhone());
        assertThat(capturedUser.getPassword()).isEqualTo(userDTO.getPassword());

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
                LocalDateTime.of(2024,8,10,6,50,10),
                LocalDateTime.of(2024,8,10,6,50,10));

                // return the existing User
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockedUser));



                //new Values for the existing user
        UserDTO userDTO = new UserDTO("updatedname",
                "updatedlastname",
                "updated@ettalbi",
                "jjjjjj",
                "00000000000");


        //when
        Long mockedUserID = mockedUser.getId();
        underTest.updateUser(mockedUserID, userDTO);


        //then

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(argumentCaptor.capture());

        User capturedUser = argumentCaptor.getValue();

        // Values that should staty equal

        assertThat(capturedUser.getFirstName()).isEqualTo(userDTO.getFirstName());
        assertThat(capturedUser.getLastName()).isEqualTo(userDTO.getLastName());
        assertThat(capturedUser.getEmail()).isEqualTo(userDTO.getEmail());
        assertThat(capturedUser.getPassword()).isEqualTo(userDTO.getPassword());
        assertThat(capturedUser.getPhone()).isEqualTo(userDTO.getPhone());
        assertThat(capturedUser.getCreatedAt()).isEqualTo(LocalDateTime.of(2024,8,10,6,50,10)) ;

       //the value that should be different
        assertThat(capturedUser.getUpdatedAt()).isAfter(LocalDateTime.of(2024,8,10,6,50,10));



    }

    @Test
    void deleteUser() {

        //given
        Long id= 1L;
        //when
        underTest.deleteUser(id);
        //then
        verify(userRepository).deleteById(id);

    }
}
