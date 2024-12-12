package com.example.retoCodificacion.repository;

import com.example.retoCodificacion.model.User;  // Tu clase de usuario
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);  // Método para encontrar un usuario por su nombre de usuario
}
