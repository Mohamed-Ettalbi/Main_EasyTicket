package com.Internship.Main_EasyTicket.config;


import com.Internship.Main_EasyTicket.dao.UserRepository;
import com.Internship.Main_EasyTicket.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig  {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    @Bean
    public UserDetailsService userDetailsService(){
//        return username -> userRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//

        return username -> {
            // Load the user from the database
            User user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            // Debugging: Print out the loaded user's email and hashed password
            System.out.println("Loaded user: " + user.getEmail());
            System.out.println("Stored password (hashed): " + user.getPassword());

            // Debugging: Print out the user's roles
            user.getRoles().forEach(role -> System.out.println("Role: " + role.name()));

            // Return the UserDetails object expected by Spring Security
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority(role.name()))
                            .collect(Collectors.toList())
            );
        };


//        return new UserDetailsService() {
//
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                return null;
//            }
//        }






    }

//    private Optional<UserDTO> geBytUsername(String email) {
//    ObjectMapper mapper = new ObjectMapper();
//        Optional<User> user = userRepository.findByEmail(email);
//
//       return UserMapperReturnOptional.mapToUserDTORespnse(user);
//    }



    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {

        return config.getAuthenticationManager();
    }
}
