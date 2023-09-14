package com.blood_stock_server.business.service;

import com.blood_stock_server.model.AuthenticationRequest;
import com.blood_stock_server.model.AuthenticationResponse;
import com.blood_stock_server.model.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
