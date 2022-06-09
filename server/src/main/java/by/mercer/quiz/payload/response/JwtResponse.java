package by.mercer.quiz.payload.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtResponse {

    private static final String TYPE = "Bearer";

    private final String token;
    private final Long id;
    private final String username;
    private final String firstName;
    private final String lastName;
}