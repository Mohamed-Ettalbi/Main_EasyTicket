package com.Internship.Main_EasyTicket.Service;
import com.Internship.Main_EasyTicket.DAO.TechnicianRepository;
import com.Internship.Main_EasyTicket.DTO.Mapper.TechnicianMapper;
import com.Internship.Main_EasyTicket.DTO.Request.UserDTORequest;
import com.Internship.Main_EasyTicket.DTO.Response.TechnicianDTOResponse;
import com.Internship.Main_EasyTicket.DTO.Response.UserDTOResponse;
import com.Internship.Main_EasyTicket.Exceptions.DuplicateEmailException;
import com.Internship.Main_EasyTicket.Exceptions.TechnicianNotFoundException;
import com.Internship.Main_EasyTicket.model.Technician;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TechnicianService {




    @Autowired
    private  TechnicianRepository technicianRepository;

    public UserDTOResponse createTechnician(UserDTORequest request) {

        boolean emailExists = technicianRepository.existsByEmailIgnoreCase(request.getEmail());
        if (emailExists) {
            throw new DuplicateEmailException("the email  " + request.getEmail() + " is already In Use ");
        } else {
            Technician technician = new Technician();
            technician.setFirstName(request.getFirstName());
            technician.setLastName(request.getLastName());
            technician.setEmail(request.getEmail());
            technician.setPassword(request.getPassword());
            technician.setPhone(request.getPhone());

            LocalDateTime now = LocalDateTime.now();
            technician.setCreatedAt(now);
            technician.setUpdatedAt(now);

           technicianRepository.save(technician);
            return new UserDTOResponse(technician.getId(),
                    technician.getFirstName(),
                    technician.getLastName(),
                    technician.getEmail(),
                    technician.getPhone(),
                    technician.getIsApproved());
        }

    }

    public List<TechnicianDTOResponse> getAllTechnicians() {

        List<Technician> techniciansList = technicianRepository.findAll();
        List<TechnicianDTOResponse> technicianDTOResponseList = TechnicianMapper.mapToTechnicianDTORespnse(techniciansList);
        if (technicianDTOResponseList.isEmpty()) {

            throw new TechnicianNotFoundException("there is currently no technicians in the system");
        } else {
            return technicianDTOResponseList;
        }
    }
}

