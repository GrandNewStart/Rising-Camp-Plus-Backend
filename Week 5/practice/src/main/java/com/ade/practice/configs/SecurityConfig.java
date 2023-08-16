package com.ade.practice.configs;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ade.practice.models.CustomOAuthUser;
import com.ade.practice.services.OAuthUserService;
import com.ade.practice.services.UserService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

    @Autowired
    private OAuthUserService oAuthUserService;

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
        .authorizeHttpRequests(auth->
            auth.requestMatchers(new AntPathRequestMatcher("/secured")).authenticated()
        )
        .authorizeHttpRequests(auth->
            auth.requestMatchers(
                new AntPathRequestMatcher("/"),
                new AntPathRequestMatcher("/h2-console/**"),
                new AntPathRequestMatcher("/authenticate"),
                new AntPathRequestMatcher("/register"),
                new AntPathRequestMatcher("/login/**"),
                new AntPathRequestMatcher("/oauth/**"),
                new AntPathRequestMatcher("/users/**")
            ).permitAll()
        )
        .formLogin(form->form.disable())
        .httpBasic(Customizer.withDefaults())
        .csrf(Customizer.withDefaults())
        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .headers(headers->headers.frameOptions(frameOptions->frameOptions.sameOrigin()))
        .oauth2Login(oauth->
            oauth
                .userInfoEndpoint(endPoint->endPoint.userService(this.oAuthUserService))
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(
                        HttpServletRequest request, 
                        HttpServletResponse response,
                        Authentication authentication
                    ) throws IOException, ServletException {
                        String id = authentication.getName();
                        DefaultOidcUser user = (DefaultOidcUser) authentication.getPrincipal();
                        String name = user.getFullName();
                        String idToken = user.getIdToken().getTokenValue();
                        System.out.println(idToken);
                        try {
                            userService.createUser(id, name);
                        } catch (Exception e) { }
                        response.sendRedirect("/users/" + id);
                    }
                    
                })
        )
        .build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedMethods("*")
                    .allowedOrigins("http://localhost:8080");
            }
        };
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // KeyPair -> RSAKey -> JWKSource -> Decoder/Encoder

    @Bean
    public KeyPair keyPair() {
        try {
            var generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            return generator.generateKeyPair();
        } catch(NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public RSAKey rsaKey(KeyPair keypair) {
        return new RSAKey
        .Builder((RSAPublicKey) keypair.getPublic())
        .privateKey(keypair.getPrivate())
        .keyID(UUID.randomUUID().toString())
        .build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
        var jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, context) -> jwkSelector.select(jwkSet);
    }

    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
        return NimbusJwtDecoder
            .withPublicKey(rsaKey.toRSAPublicKey())
            .build();
    }

}
