// package com.ticketing.user_service.security.config;

// import com.ticketing.user_service.controller.AuthController;
// import com.ticketing.user_service.repository.UserRepository;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// @EnableWebSecurity
// public class ApplicationConfiguration {

//     private final UserRepository userRepository;

//     public ApplicationConfiguration(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     @Bean
//     public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authConfig) throws Exception {
//         return authConfig.getAuthenticationManager();
//     }

//     @Bean
//     UserDetailsService userDetailsService() {
//         return username -> userRepository.findByEmail(username)
//                 .orElseThrow(() -> new RuntimeException("User not found"));
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     AuthenticationProvider authenticationProvider() {
//         DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

//         authProvider.setUserDetailsService(userDetailsService());
//         authProvider.setPasswordEncoder(passwordEncoder());

//         return authProvider;
//     }

//     // protected void configure(HttpSecurity http) throws Exception {
//     //     http.csrf(csrf -> csrf.disable())
//     //             .authorizeHttpRequests(auth -> auth
//     //                     .requestMatchers("/auth/login", "/auth/register").permitAll()
//     //                     .requestMatchers("/users/**").hasAnyRole("ADMIN", "ORGANIZER", "ATTENDEE")
//     //                     .anyRequest()
//     //                     .authenticated())

//     //             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//     //             .httpBasic(httpBasic -> httpBasic.disable())
//     //             .formLogin(form -> form.disable());
//     // }

// }
