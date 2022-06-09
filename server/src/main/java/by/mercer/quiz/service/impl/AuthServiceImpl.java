package by.mercer.quiz.service.impl;

import by.mercer.quiz.domain.AppUser;
import by.mercer.quiz.payload.request.LoginRequest;
import by.mercer.quiz.payload.response.JwtResponse;
import by.mercer.quiz.repository.AppUserRepository;
import by.mercer.quiz.security.jwt.JwtUtils;
import by.mercer.quiz.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final AppUserRepository appUserRepository;

    @Override
    public JwtResponse login(LoginRequest loginRequest) {

        String jwt = authenticateUser(loginRequest);
        AppUser user = appUserRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(EntityNotFoundException::new);

        return new JwtResponse(
                jwt,
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    private String authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateJwtToken(authentication);
    }
}
