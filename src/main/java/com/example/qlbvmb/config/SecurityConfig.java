package com.example.qlbvmb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.qlbvmb.service.MySimpleUrlAuthenticationSuccessHandler;
import com.example.qlbvmb.service.QuanlyUserDetails;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private QuanlyUserDetails quanlyUserDetails;
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/*").permitAll()
                .requestMatchers("/staff/**").hasAnyAuthority("STAFF")
                .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")

                .anyRequest().authenticated())
            .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("userName")
                .passwordParameter("passWord")
                .successHandler(myAuthenticationSuccessHandler()))
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login"));
        return http.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/favicon/**", "/img/**", "/js/**", "/booking/**", "/partner/**");
    }

}
