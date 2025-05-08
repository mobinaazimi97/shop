package com.mftplus.shop.config;

import com.mftplus.shop.user.service.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.web.server.header.ContentSecurityPolicyServerHttpHeadersWriter.CONTENT_SECURITY_POLICY;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    private final CustomUserDetailsService userDetailsService;
    private final Environment environment;


    // Constants for header values used in HTTP security headers
    public static final String CONTENT_SECURITY_POLICY = "default-src 'self'; script-src 'self'; object-src 'none'; style-src 'self'; img-src 'self'; frame-ancestors 'self';";
    public static final String X_CONTENT_TYPE_OPTIONS = "nosniff";
    public static final String X_XSS_PROTECTION = "1; mode=block";
    public static final String CACHE_CONTROL = "no-store, no-cache, must-revalidate, max-age=0";
    public static final String PRAGMA = "no-cache";
    public static final String EXPIRES = "0";
    public static final String STRICT_TRANSPORT_SECURITY = "max-age=31536000; includeSubDomains";

    // Constructor to initialize services and environment
    public SecurityConfig(CustomUserDetailsService userDetailsService, Environment environment) {
        this.userDetailsService = userDetailsService;
        this.environment = environment;
    }

    // Bean for PasswordEncoder using BCrypt hashing
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // BCrypt with strength of 12
    }

    // Bean for SecurityFilterChain to configure security filters
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Check for active profiles to adjust configuration
        boolean isDevProfile = Arrays.asList(environment.getActiveProfiles()).contains("dev");

        http
                // CORS Configuration
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS with custom configuration

                // Disable CSRF Protection (needed for stateless APIs)
                .csrf(AbstractHttpConfigurer::disable)

                // Configure HTTP headers for security
                .headers(headers -> {
                    headers
                            .addHeaderWriter(new StaticHeadersWriter("Content-Security-Policy", CONTENT_SECURITY_POLICY)) // Content Security Policy
                            .addHeaderWriter(new StaticHeadersWriter("X-Content-Type-Options", X_CONTENT_TYPE_OPTIONS)) // Prevent MIME type sniffing
                            .addHeaderWriter(new StaticHeadersWriter("X-XSS-Protection", X_XSS_PROTECTION)) // Enable XSS Protection
                            .addHeaderWriter(new StaticHeadersWriter("Cache-Control", CACHE_CONTROL)) // Disable caching
                            .addHeaderWriter(new StaticHeadersWriter("Pragma", PRAGMA)) // Disable caching for old browsers
                            .addHeaderWriter(new StaticHeadersWriter("Expires", EXPIRES)) // Expiry for caching
                            .defaultsDisabled(); // Disable defaults for header configuration
                    if (isDevProfile) {
                        headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable); // Disable frame options in development
                    } else {
                        headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin) // Enable frame options in production
                                .addHeaderWriter(new StaticHeadersWriter("Strict-Transport-Security", STRICT_TRANSPORT_SECURITY)); // HSTS for secure connections
                    }
                })

                // Session Management (Protect against session fixation and hijacking)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Session created only if required
                        .sessionFixation().migrateSession() // Prevent session fixation
                        .maximumSessions(1) // Allow only one active session per user
                        .maxSessionsPreventsLogin(false) // Allow login even when session limit is reached
                        .expiredUrl("/login?expired") // Redirect to login page if session expires
                )

                // Authorization Rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/api/**", "/h2-console/**", "/public/**", "/api/test/public/**").permitAll() // Allow public access to certain paths
                        .requestMatchers("/user/**").hasRole("USER") // Require USER role for /user paths
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Require ADMIN role for /admin paths
                        .requestMatchers("/api/data").hasAuthority("READ_DATA") // Require READ_DATA authority for /api/data
                        .anyRequest().authenticated() // All other requests must be authenticated
                )

                // Form Login Configuration
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page
                        .defaultSuccessUrl("/home", true) // Redirect to home on successful login
                        .failureUrl("/login?error") // Redirect to login with error on failure
                        .permitAll() // Allow access to login page
                )

                // Logout Configuration
                .logout(logout -> logout
                        .logoutUrl("/logout") // Logout URL
                        .logoutSuccessUrl("/login?logout") // Redirect to login on successful logout
                        .invalidateHttpSession(true) // Invalidate session on logout
                        .clearAuthentication(true) // Clear authentication details
                        .deleteCookies("JSESSIONID") // Delete session cookie
                        .addLogoutHandler((request, response, authentication) -> {
                            if (authentication != null) {
                                logger.info("User {} logged out at {}", authentication.getName(), LocalDateTime.now()); // Log logout event
                            }
                            removeCustomCookie(response);  // Remove custom cookie after logout
                        })
                        .permitAll() // Allow access to logout URL
                );

        return http.build(); // Build the security configuration
    }

    // Bean for DaoAuthenticationProvider for authentication
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService); // Set custom user details service
        provider.setPasswordEncoder(passwordEncoder()); // Set password encoder
        return provider;
    }

    // Bean for AuthenticationManager to manage authentication
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager(); // Return authentication manager
    }

    // CORS Configuration Source Bean
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("https://localhost:8443")); // Allow specific origin
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow specific methods
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With")); // Allow specific headers
        configuration.setExposedHeaders(List.of("Authorization")); // Expose specific headers to client
        configuration.setAllowCredentials(true); // Allow credentials to be sent
        configuration.setMaxAge(3600L); // Cache preflight requests for 1 hour
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Register CORS configuration for all paths
        return source;
    }

    // Method to remove custom cookies on logout
    private void removeCustomCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("myCustomCookie", null); // Create cookie with null value
        cookie.setPath("/"); // Set the cookie path
        cookie.setHttpOnly(true); // Set cookie as HttpOnly
        cookie.setSecure(true); // Set cookie as Secure
        cookie.setMaxAge(0); // Set cookie expiration to 0 to delete it
        response.addCookie(cookie); // Add cookie to response
    }
}
