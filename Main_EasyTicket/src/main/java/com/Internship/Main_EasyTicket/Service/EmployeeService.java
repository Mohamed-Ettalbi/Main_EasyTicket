package com.Internship.Main_EasyTicket.Service;


import com.Internship.Main_EasyTicket.DTO.UserDTOResponse;
import com.Internship.Main_EasyTicket.dao.EmployeeRepository;
import com.Internship.Main_EasyTicket.DTO.Mapper.EmployeeListMapper;
import com.Internship.Main_EasyTicket.DTO.Mapper.UserMapper;
import com.Internship.Main_EasyTicket.DTO.UserDTO;
import com.Internship.Main_EasyTicket.Exceptions.DuplicateEmailException;
import com.Internship.Main_EasyTicket.Exceptions.TechnicianNotFoundException;
import com.Internship.Main_EasyTicket.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public UserDTOResponse createEmployee(UserDTO request) {

        boolean emailExists = employeeRepository.existsByEmailIgnoreCase(request.getEmail());
        if (emailExists) {
            throw new DuplicateEmailException("the email  " + request.getEmail() + " is already In Use ");
        } else {
            Employee employee = new Employee();
            employee.setFirstName(request.getFirstName());
            employee.setLastName(request.getLastName());
            employee.setEmail(request.getEmail());
            employee.setPassword(request.getPassword());
            employee.setPhone(request.getPhone());

            LocalDateTime now = LocalDateTime.now();
            employee.setCreatedAt(now);
            employee.setUpdatedAt(now);

            employeeRepository.save(employee);
            UserDTOResponse response = UserMapper.mapToUserDTORespnse(employee);

            return response;
        }


    }
    public List<UserDTOResponse> getAllEmployee() {

        List<Employee> employeeList = employeeRepository.findAll();
        List<UserDTOResponse> employeeDTOResponseList = EmployeeListMapper.mapToUserDTORespnse(employeeList);
        if (employeeDTOResponseList.isEmpty()) {

            throw new TechnicianNotFoundException("there is currently no technicians in the system");
        } else {
            return employeeDTOResponseList;
        }
    }


}
