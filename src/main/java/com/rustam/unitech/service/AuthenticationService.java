package com.rustam.unitech.service;

import com.rustam.unitech.dto.request.user.AuthenticationRequest;
import com.rustam.unitech.dto.response.AuthenticationResponse;
import com.rustam.unitech.dto.request.user.RegisterRequest;
import com.rustam.unitech.entity.User;
import com.rustam.unitech.enums.ResponseDetails;
import com.rustam.unitech.enums.Role;
import com.rustam.unitech.exception.UniTechException;
import com.rustam.unitech.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    //I know that it s not a good practice to return ENUM type, just for testing purpose for demo app
    public ResponseDetails register(RegisterRequest request) {
        boolean isUserExist = userService.isExist(request.getEmail());
        if (isUserExist)
            throw new UniTechException(ResponseDetails.USERNAME_EXIST);

        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        //just for our simple UniTech application we don't provide another logic to create user with another role
        user.setRole(Role.USER);
        userService.create(user);
        return ResponseDetails.USER_REGISTERED;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        UserDetails user = userService.loadUserByUsername(request.getEmail());
        final String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
