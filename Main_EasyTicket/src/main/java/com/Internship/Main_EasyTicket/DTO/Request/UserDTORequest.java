package com.Internship.Main_EasyTicket.DTO.Request;

import jakarta.validation.constraints.*;

public class UserDTORequest {

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



    public UserDTORequest(String firstName, String lastName, String email, String phone, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public UserDTORequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}


