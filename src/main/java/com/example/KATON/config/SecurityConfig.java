package com.example.KATON.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {

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
    private InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user,user1,user2,user3);
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                    .requestMatchers("/","/ws/**")
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
