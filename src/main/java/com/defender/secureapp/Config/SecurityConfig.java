package com.defender.secureapp.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.defender.secureapp.service.StudentService;
import com.defender.secureapp.utils.STUDENT_ROLE;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                (authorize) -> authorize.requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/master/**").hasRole(STUDENT_ROLE.ADMIN.getRole())
                .anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
        http.csrf(t -> t.disable());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(StudentService studentService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(studentService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(daoAuthenticationProvider);
    }

    // @Bean
    // public UserDetailsService inMemoryUserDetailsService() {
    //     UserDetails userDetails = User.withUsername("abhishek").password(passwordEncoder().encode("secret"))
    //             .authorities("Role").build();
    //             System.out.println("pass == "+userDetails.getPassword());
    //     return new InMemoryUserDetailsManager(userDetails);
    // }

    

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
