package ru.halal.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.halal.market.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
