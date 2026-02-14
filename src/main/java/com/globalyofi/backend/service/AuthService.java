package com.globalyofi.backend.service;

import com.globalyofi.backend.entity.Usuario;
import com.globalyofi.backend.repository.UsuarioRepository;
import com.globalyofi.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    //Registrar un nuevo usuario en el sistema
    public Usuario registrar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    //Iniciar sesión y generar un token JWT
    public Map<String, Object> login(String email, String contrasena) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(email, contrasena));
            Usuario usuario = usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRol());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("rol", usuario.getRol());
            response.put("nombre", usuario.getNombre());
            response.put("email", usuario.getEmail());
            return response;

        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciales inválidas");
        }
    }
}
