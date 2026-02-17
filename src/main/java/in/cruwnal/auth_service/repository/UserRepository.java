package in.cruwnal.auth_service.repository;

import in.cruwnal.auth_service.dto.AuthResponse;
import in.cruwnal.auth_service.dto.UserResponse;
import in.cruwnal.auth_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
