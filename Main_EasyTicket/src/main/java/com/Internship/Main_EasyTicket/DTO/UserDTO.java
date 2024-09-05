package com.Internship.Main_EasyTicket.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

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
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=!]).{8,64}$",
            message = "Password must contain at least one digit, one lowercase letter, and one special character"
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean isApproved;

    @Pattern(regexp = "TECHNICIAN|EMPLOYEE|ADMIN", message = "Role must be TECHNICIAN, EMPLOYEE, or ADMIN")
    @NotBlank
    private String role;




}


