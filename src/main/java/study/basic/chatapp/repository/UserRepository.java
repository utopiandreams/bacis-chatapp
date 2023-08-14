package study.basic.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.basic.chatapp.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
