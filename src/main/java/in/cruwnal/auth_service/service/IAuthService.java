package in.cruwnal.auth_service.service;

import in.cruwnal.auth_service.dto.AuthResponse;
import in.cruwnal.auth_service.dto.LoginRequest;
import in.cruwnal.auth_service.dto.RegisterRequest;
import in.cruwnal.auth_service.dto.UserResponse;

public interface IAuthService {
    UserResponse registerUser(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
