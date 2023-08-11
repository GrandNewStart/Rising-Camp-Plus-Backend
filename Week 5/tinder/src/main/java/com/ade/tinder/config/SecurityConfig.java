package com.ade.tinder.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth->{
            auth.anyRequest().authenticated();
        });
        http.sessionManagement(session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        http.httpBasic(basic -> {});
        http.csrf(csrf->{
            csrf.disable();
        });
        http.headers(security->{
            security.frameOptions(frame->{
                frame.sameOrigin();
            });
        });
        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:3000");
            }
        };
    }

    // @Bean
    // public UserDetailsService userDetailsService(DataSource source) {
        // var user = User.withUsername("ade")
        //     .password("{noop}haha")
        //     .roles("USER")
        //     .build();
        // var admin = User.withUsername("admin")
        //     .password("{noop}haha")
        //     .roles("ADMIN")
        //     .build();
    //     return new InMemoryUserDetailsManager(user, admin);
    // }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
            .build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        var user = User.withUsername("ade")
            .password("{noop}haha")
            .roles("USER")
            .build();
        var admin = User.withUsername("admin")
            .password("{noop}haha")
            .roles("ADMIN")
            .build();

         var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
         jdbcUserDetailsManager.createUser(user);
         jdbcUserDetailsManager.createUser(admin);

         return jdbcUserDetailsManager;
    }
    
}