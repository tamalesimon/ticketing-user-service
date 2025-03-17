// package com.ticketing.user_service.security;

// import java.time.Instant;

// import org.springframework.context.annotation.Lazy;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.ticketing.user_service.dto.SigninRequest;
// import com.ticketing.user_service.dto.SigninResponse;
// import com.ticketing.user_service.dto.SignupRequest;
// import com.ticketing.user_service.dto.SignupResponse;
// import com.ticketing.user_service.entity.Role;
// import com.ticketing.user_service.entity.RoleType;
// import com.ticketing.user_service.entity.User;
// import com.ticketing.user_service.entity.UserStatus;
// import com.ticketing.user_service.repository.RoleRepository;
// import com.ticketing.user_service.repository.UserRepository;

// @Service
// public class AuthService implements UserDetailsService {

//         final UserRepository userRepository;

//         final RoleRepository roleRepository;

//         final JwtUtil jwtUtil;

//         final AuthenticationManager authenticationManager;

//         final PasswordEncoder passwordEncoder;

//         public AuthService(UserRepository userRepository, JwtUtil jwtUtil, @Lazy AuthenticationManager authenticationManager,
//                         PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
//                 this.userRepository = userRepository;
//                 this.jwtUtil = jwtUtil;
//                 this.authenticationManager = authenticationManager;
//                 this.passwordEncoder = passwordEncoder;
//                 this.roleRepository = roleRepository;
//         }

//         public SigninResponse authenticate(SigninRequest request) {
//                 String email = request.getEmail();
//                 String password = request.getPassword();

//                 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

//                 UserDetails user = loadUserByUsername(email);
//                 String token = jwtUtil.generateToken(user.getUsername());
//                 Instant expiresAt = Instant.now().plusMillis(jwtUtil.extractTokenExpirationDate(token).getTime());
//                 String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());
//                 Instant refreshExpiresAt = Instant.now()
//                                 .plusMillis(jwtUtil.extractTokenExpirationDate(refreshToken).getTime());

//                 User loggedinUser = userRepository.findByEmail(email)
//                                 .orElseThrow(() -> new UsernameNotFoundException("User not found"));

//                 return SigninResponse.builder()
//                                 .userId(loggedinUser.getId())
//                                 .name(loggedinUser.getName())
//                                 .email(loggedinUser.getEmail())
//                                 .role(loggedinUser.getRoles().getName().name())
//                                 .userStatus(loggedinUser.getStatus().name())
//                                 .token(token)
//                                 .expiresAt(expiresAt)
//                                 .refreshToken(refreshToken)
//                                 .refreshTokenExpiresAt(refreshExpiresAt)
//                                 .build();
//         }

//         public SignupResponse register(SignupRequest request) {

//                 Role role = roleRepository.findByName(RoleType.valueOf(request.getRole()))
//                                 .orElseThrow(() -> new RuntimeException("Invalid role: " + request.getRole()));

//                 User user = new User();
//                 user.setName(request.getName());
//                 user.setEmail(request.getEmail());
//                 user.setHashedPassword(passwordEncoder.encode(request.getPassword()));
//                 user.setStatus(UserStatus.PENDING_VERIFICATION);
//                 user.setRoles(role);

//                 request.setPassword(passwordEncoder.encode(request.getPassword()));

//                 User savedUser = userRepository.save(user);
//                 return SignupResponse.builder()
//                                 .userId(savedUser.getId())
//                                 .name(savedUser.getName())
//                                 .email(savedUser.getEmail())
//                                 .status(savedUser.getStatus())
//                                 .build();
//         }

//         @Override
//         public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//                 User user = userRepository.findByEmail(email)
//                                 .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//                 return org.springframework.security.core.userdetails.User
//                                 .withUsername(user.getEmail())
//                                 .password(user.getHashedPassword())
//                                 .roles(user.getRoles().getName().name())
//                                 .build();
//         }

// }
