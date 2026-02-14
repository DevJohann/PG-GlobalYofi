package com.globalyofi.backend.config;

import com.globalyofi.backend.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    /**
     * 🧩 Configuración principal de seguridad
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // ✅ Habilitamos CORS con el bean configurado más abajo
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 🚫 Deshabilitamos CSRF (porque usamos JWT)
                .csrf(csrf -> csrf.disable())

                // 🧠 Política de sesiones sin estado (JWT)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 🔐 Reglas de autorización
                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas (no requieren token)
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(
                                "/api/productos/**",
                                "/api/categorias/**",
                                "/api/proveedores/**",
                                "/uploads/**"
                        ).permitAll()

                        // Todo lo demás requiere autenticación
                        .anyRequest().authenticated()
                )

                // 🧱 Agregamos nuestro filtro JWT antes del filtro estándar
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * 🔑 Bean para manejar el CORS (Angular <-> Spring Boot)
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // 🌐 Dominio del frontend
        config.setAllowedOrigins(List.of("http://localhost:4200"));

        // ✅ Métodos permitidos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // ✅ Headers permitidos
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        // ✅ Headers expuestos (para leer el token si se enviara por header)
        config.setExposedHeaders(List.of("Authorization"));

        // ⚙️ Permitir cookies si lo necesitaras (por ahora no)
        config.setAllowCredentials(true);

        // Registrar configuración para todas las rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    /**
     * 🔐 Codificador de contraseñas (BCrypt)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * ⚙️ AuthenticationManager para login
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
