package com.example.banking.Controller;

import com.example.banking.Entity.User;
import com.example.banking.ExceptionHandler.UserException;
import com.example.banking.Repository.UserRepository;
import com.example.banking.model.Role;
import com.example.banking.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173/")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            // Load user details to generate JWT
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return jwtUtil.generateToken(userDetails.getUsername());

        } catch (BadCredentialsException e) {
            throw new UserException("User not found");
            
        }
    }

    @PostMapping("/signup")
    public String signup(@RequestBody Map<String,String> credentials){
        String username = credentials.get("username");
        String password = credentials.get("password");
        String role = credentials.get("role");
        if (userRepository.findByUsername(username).isPresent()){
            return "Error: Username already exists";
        }
        Role userRole;
        try{
            userRole=Role.valueOf(role.toUpperCase());
        }catch (IllegalArgumentException e){
            return "Error: Invalid role. Use USER or ADMIN";
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password)); // Encrypt password
        newUser.setRole(userRole);
        userRepository.save(newUser);
        return "User registered successfully!";

    }

}
