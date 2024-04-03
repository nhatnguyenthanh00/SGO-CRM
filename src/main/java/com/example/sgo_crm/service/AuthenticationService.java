package com.example.sgo_crm.service;

import com.example.sgo_crm.request.AuthenticationRequest;

public interface AuthenticationService {

    String authenticate(AuthenticationRequest request);

}
