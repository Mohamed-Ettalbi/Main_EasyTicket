package com.Internship.Main_EasyTicket.Service;


import com.Internship.Main_EasyTicket.dao.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

        @Autowired
        private  AdminRepository adminRepository;


}

