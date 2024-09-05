package com.Internship.Main_EasyTicket.controller;

import com.Internship.Main_EasyTicket.DTO.UserDTO;
import com.Internship.Main_EasyTicket.DTO.Response.TechnicianDTOResponse;
import com.Internship.Main_EasyTicket.Service.TechnicianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/technician")
public class TechnicianController {

    @Autowired
    public TechnicianService technicianService;

    @PostMapping("/add")
    public ResponseEntity<TechnicianDTOResponse> createTechnician(@RequestBody @Valid UserDTO request){


        TechnicianDTOResponse technician=technicianService.createTechnician(request);
        return new ResponseEntity<>(technician, HttpStatus.CREATED);




    }
    @GetMapping("/all")
    public ResponseEntity<List<TechnicianDTOResponse>> getAllTechnicians(){

        List<TechnicianDTOResponse> technicianList = technicianService.getAllTechnicians();
        return new ResponseEntity<>(technicianList,HttpStatus.OK);



    }
    @GetMapping()
    public ResponseEntity<TechnicianDTOResponse> getTechnicianByEmail(@RequestParam("email") String email){

        TechnicianDTOResponse technicianList = technicianService.getTechnicianByEmail(email);
        return new ResponseEntity<>(technicianList,HttpStatus.OK);



    }


}
