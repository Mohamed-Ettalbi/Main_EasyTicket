package com.Internship.Main_EasyTicket.Service;


import com.Internship.Main_EasyTicket.DAO.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {


        private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


}

