//package com.Internship.Main_EasyTicket.config;
//
//    import com.Internship.Main_EasyTicket.dao.UserRepository;
//    import com.Internship.Main_EasyTicket.model.Admin;
//    import com.Internship.Main_EasyTicket.model.Role;
//    import com.Internship.Main_EasyTicket.model.User;
//    import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;
//
//    import java.time.LocalDateTime;
//    import java.util.Set;
//
//@Configuration
//    public class AdminUserSetup {
//
//        @Autowired
//        private UserRepository userRepository;
//
//        @Autowired
//        private PasswordEncoder passwordEncoder;
//
//        @Bean
//        public CommandLineRunner insertAdminUser() {
//            return args -> {
//                String adminEmail = "admin@gmail.com";
//
//                // Check if admin user already exists
//
//                    // Admin user details
//                    Admin adminUser = Admin.builder()
//                            .firstName("Admin")
//                            .lastName("User")
//                            .email(adminEmail)
//                            .phone("1234567890")
//                            .password(passwordEncoder.encode("A1&aaaaa"))
//                            .roles(Set.of(Role.ROLE_ADMIN))
//                            .isApproved(true)
//                            .createdAt(LocalDateTime.now())
//                            .updatedAt(LocalDateTime.now())
//                            .build();
//
//                    // Save the admin user
//                    userRepository.save(adminUser);
//
//                    System.out.println("Admin user created successfully.");
//
//            };
//        }
//    }
//
