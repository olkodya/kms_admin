package com.example.kms.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.example.kms.entity.Role.ADMIN;
import static com.example.kms.entity.Role.USER;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authProvider;
    private final LogoutHandler logoutHandler;

    private static final String[] WHITE_LIST_URL = {"/api/users/auth",
            "/api/users/refresh-token",
            "/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/**",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/swagger-ui.html/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(customizer -> customizer
                .requestMatchers(WHITE_LIST_URL).permitAll()
                                .requestMatchers("/api/**").permitAll()
/*

                .requestMatchers(HttpMethod.POST, "/api/users/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole(USER.name(), ADMIN.name())

                .requestMatchers(HttpMethod.POST, "/api/audiences/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.PUT, "/api/audiences/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.GET, "/api/audiences/**").hasAnyRole(USER.name(), ADMIN.name())

                .requestMatchers(HttpMethod.POST, "/api/divisions/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.PUT, "/api/divisions/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.GET, "/api/divisions/**").hasAnyRole(USER.name(), ADMIN.name())

                .requestMatchers(HttpMethod.POST,"/api/employees/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.PUT,"/api/employees/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.GET,"/api/employees/**").hasAnyRole(USER.name(), ADMIN.name())

                .requestMatchers("/api/QRs/**").hasAnyRole(ADMIN.name(), USER.name())

                .requestMatchers(HttpMethod.POST,"/api/employeeIds/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.PUT,"/api/employeeIds/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.GET,"/api/employeeIds/**").hasAnyRole(USER.name(), ADMIN.name())

                .requestMatchers(HttpMethod.DELETE,"/api/images/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.GET,"/api/images/**").hasAnyRole(USER.name(), ADMIN.name())
                .requestMatchers(HttpMethod.POST,"/api/images/**").hasAnyRole(USER.name(), ADMIN.name())

                .requestMatchers(HttpMethod.POST,"/api/keys/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.PUT,"/api/keys/**").hasAnyRole(USER.name(), ADMIN.name())
                .requestMatchers(HttpMethod.GET,"/api/keys/**").hasAnyRole(USER.name(), ADMIN.name())

                .requestMatchers("/api/operations/**").hasAnyRole(ADMIN.name(), USER.name())

                .requestMatchers(HttpMethod.POST, "/api/permissions/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.PUT, "/api/permissions/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.DELETE, "/api/permissions/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.GET, "/api/permissions/**").hasAnyRole(USER.name(), ADMIN.name())

                .requestMatchers("/api/shifts/**").hasAnyRole(ADMIN.name(), USER.name())

                .requestMatchers("/api/signatures/**").hasAnyRole(ADMIN.name(), USER.name())

                .requestMatchers(HttpMethod.POST, "/api/watches/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.PUT, "/api/watches/**").hasRole(ADMIN.name())
                .requestMatchers(HttpMethod.GET, "/api/watches/**").hasAnyRole(USER.name(), ADMIN.name())
*/

                .anyRequest()
                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
        ;
        return http.build();
    }

}
