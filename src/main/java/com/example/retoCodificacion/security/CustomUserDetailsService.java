package com.example.retoCodificacion.security;

import com.example.retoCodificacion.model.User;  // Tu clase de usuario
import com.example.retoCodificacion.repository.UserRepository;  // Repositorio para acceder a los datos del usuario
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;  // Accede a los datos del usuario

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca el usuario por el nombre de usuario
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Aquí puedes personalizar cómo construir el objeto UserDetails
        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
        userBuilder.password(user.getPassword());  // Contraseña del usuario
        userBuilder.roles(user.getRoles().toArray(new String[0]));  // Si el usuario tiene roles, agregar aquí

        return userBuilder.build();
    }
}
