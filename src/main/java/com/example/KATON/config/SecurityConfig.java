package com.example.KATON.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Configuration
public class SecurityConfig {

    UserDetails user1 = User.withDefaultPasswordEncoder()
            .username("diego")
            .password("diego")
            .roles("USER")
            .build();

    private InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1);


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        Path currentDirectoryPath = FileSystems.getDefault().getPath("");
        String currentDirectoryName = currentDirectoryPath.toAbsolutePath().toString();
        BufferedReader reader = new BufferedReader(new FileReader(currentDirectoryName+"/src/main/resources/database/sicurezza"));
        String currentLine = reader.readLine();
        while ((currentLine != "")&&(currentLine != null)) {
            String[] parts = currentLine.split("-");
            UserDetails user = User.withDefaultPasswordEncoder()
                    .username(parts[0])
                    .password(parts[1])
                    .roles("USER")
                    .build();
            currentLine = reader.readLine();
            inMemoryUserDetailsManager.createUser(user);
        }
        reader.close();
        http
                .authorizeHttpRequests()
                    .requestMatchers("/","/ws/**","/css/homeCSS.css")
                    .permitAll()
                .and()
                .authorizeHttpRequests()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout( logout -> logout.logoutSuccessUrl("/"));
        return http.build();
    }

    /*@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("diego")
                .password("diego")
                .roles("USER")
                .build();
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("cassa")
                .password("cassa")
                .roles("USER")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("primi")
                .password("primi")
                .roles("USER")
                .build();
        UserDetails user3 = User.withDefaultPasswordEncoder()
                .username("secondi")
                .password("secondi")
                .roles("USER")
                .build();
        inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user,user1,user2,user3);
        return inMemoryUserDetailsManager;
    }*/

    @Bean
    public InMemoryUserDetailsManager getUserDetailsService() {
        return inMemoryUserDetailsManager;
    }
}
