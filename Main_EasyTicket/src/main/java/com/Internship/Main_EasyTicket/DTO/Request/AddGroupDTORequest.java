package com.Internship.Main_EasyTicket.DTO.Request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddGroupDTORequest {


    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[A-Za-z0-9 _-]{3,50}$", message = "Group name must be between 3 and 50 characters long and can only contain letters, numbers, spaces, underscores, and hyphens.")
    private String groupName;

    @Column(nullable = false)
    @Size(max = 2500 )
    private String groupDescription;




}
