package in.cruwnal.auth_service.service;

import in.cruwnal.auth_service.dto.AuthResponse;
import in.cruwnal.auth_service.dto.LoginRequest;
import in.cruwnal.auth_service.dto.RegisterRequest;
import in.cruwnal.auth_service.dto.UserResponse;
import in.cruwnal.auth_service.entity.User;
import in.cruwnal.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserResponse registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = User.builder()
                .email(request.email())
                // Ensure your User entity has this field!
                .fullName(request.fullName())
                .password(passwordEncoder.encode(request.password()))
                .roles(Set.of("ROLE_USER"))
                .enabled(true)
                .build();

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getFullName(),
                savedUser.getRoles()
        );
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        // This triggers your CustomUserDetailsService and PasswordEncoder check
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        // Security Tip: Cast the principal to your User entity
        // This works if your CustomUserDetailsService returns your User entity
        User user = (User) authentication.getPrincipal();

        String accessToken = jwtService.generateToken(user.getEmail());
        String refreshToken = jwtService.generateRefreshToken(user.getEmail());

        return new AuthResponse(
                accessToken,
                refreshToken,
                user.getEmail(),
                user.getRoles()
        );
    }
}

