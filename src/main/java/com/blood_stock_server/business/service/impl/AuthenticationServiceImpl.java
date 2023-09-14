package com.blood_stock_server.business.service.impl;

import com.blood_stock_server.business.exceptions.ExistInDataBaseException;
import com.blood_stock_server.business.mappers.UserMapper;
import com.blood_stock_server.business.repository.UserRepository;
import com.blood_stock_server.business.repository.model.Role;
import com.blood_stock_server.business.service.AuthenticationService;
import com.blood_stock_server.business.service.JwtService;
import com.blood_stock_server.model.AuthenticationRequest;
import com.blood_stock_server.model.AuthenticationResponse;
import com.blood_stock_server.model.RegisterRequest;
import com.blood_stock_server.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        if (isExistInRepository(request.getEmail()))
            throw new ExistInDataBaseException("This email: " + request.getEmail() + " already exist");
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(mapper.userToUserEntity(user));
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .flatMap(u -> Optional.ofNullable(mapper.userEntityToUser(u)))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    private boolean isExistInRepository(String email) {
        return repository.existsByEmail(email);
    }
}
