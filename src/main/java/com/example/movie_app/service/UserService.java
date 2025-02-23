package com.example.movie_app.service;

import com.example.movie_app.model.User;
import com.example.movie_app.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, JWTService jwtService){
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User register(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String verify(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());
        else
            return "Failure";
    }
}
