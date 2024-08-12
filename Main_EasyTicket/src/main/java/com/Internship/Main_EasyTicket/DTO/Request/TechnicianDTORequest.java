package com.Internship.Main_EasyTicket.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechnicianDTORequest {

    @NotBlank(message = "first name can not be blank")
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Z\\s-]+$", message = "message = \"Name can only contain letters, spaces, or hyphens")
    private String firstName;

    @NotBlank(message = "last name can not be blank ")
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Z\\s-]+$", message = "message = \"Name can only contain letters, spaces, or hyphens")
    private String lastName;

    @NotBlank(message = "this field can not be blank")
    @Email(message = "this must be a valid email")
    @Size(max = 100)
    private String email;

    @NotBlank(message = "this field can not be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Don't add the country code just the number straight")
    private String phone;

    @NotBlank(message = "this field can not be blank")
    @Size(min = 8, max = 64)
    private String password;





}
