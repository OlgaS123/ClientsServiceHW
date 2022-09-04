package com.example.clientsservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    //@Bean
    public InMemoryUserDetailsManager userDetailsManager(BCryptPasswordEncoder encoder){
        return new InMemoryUserDetailsManager(
                User.builder()
                        .passwordEncoder(encoder::encode)
                        .username("u")
                        .password("p")
                        .roles(com.example.clientsservice.models.User.Role.USER.name())
                        .build(),
                User.builder()
                        .passwordEncoder(encoder::encode)
                        .username("a")
                        .password("p")
                        .roles(com.example.clientsservice.models.User.Role.ADMIN.name())
                        .build()
        );
    }
    //@Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return customizer->customizer.debug(true)
                .ignoring()
                //.anyRequest()
                .antMatchers("/css/**")
                .antMatchers("/registration")
                .mvcMatchers(HttpMethod.POST,"/registration");
    }
   @Bean
   public AuthenticationManager authManager(
            HttpSecurity http,
            BCryptPasswordEncoder encoder,
            UserDetailsService userDetailsService
   )throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder)
                .and().build();
   }

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
       http
               .authorizeRequests()
               .antMatchers("/error","/registration")
               .permitAll()
               .antMatchers("/clients")
               .hasAnyAuthority(com.example.clientsservice.models.User.Role.USER.name(),
                       com.example.clientsservice.models.User.Role.ADMIN.name())
               .antMatchers("/users")
               .hasAnyAuthority(com.example.clientsservice.models.User.Role.ADMIN.name())
               .and()
               .formLogin()
               .loginPage("/authorization")
               .permitAll()
               .and()
               .logout()
               .logoutUrl("/j_spring_security_logout")
               .logoutSuccessUrl("/authorization");
       return http.build();
   }
}
