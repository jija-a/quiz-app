package by.mercer.quiz.service;

import by.mercer.quiz.payload.request.LoginRequest;
import by.mercer.quiz.payload.response.JwtResponse;

public interface AuthService {

    JwtResponse login(LoginRequest loginRequest);
}
