package in.cruwnal.auth_service.dto;

import java.util.Set;

public record AuthResponse(
        String accessToken,
        String refreshToken,
        String email,
        Set<String> roles
) {}
