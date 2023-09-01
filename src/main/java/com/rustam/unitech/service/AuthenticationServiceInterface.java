package com.rustam.unitech.service;

import com.rustam.unitech.dto.request.user.AuthenticationRequest;
import com.rustam.unitech.dto.request.user.RegisterRequest;
import com.rustam.unitech.dto.response.AuthenticationResponse;

public interface AuthenticationServiceInterface {
     AuthenticationResponse authenticate(final AuthenticationRequest request);

    String register(final RegisterRequest request);
}
