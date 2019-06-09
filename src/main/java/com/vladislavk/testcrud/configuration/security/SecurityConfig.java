package com.vladislavk.testcrud.configuration.security;

import com.vladislavk.testcrud.configuration.jwt.JWTAuthEntryPoint;
import com.vladislavk.testcrud.configuration.jwt.JWTAuthFilter;
import com.vladislavk.testcrud.configuration.jwt.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Vladislav Klochkov
 * @project testcrud
 * @date 2019-06-06
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTAuthEntryPoint jwtAuthEntryPoint;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String cryptPassword = passwordEncoder().encode("123");

        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder>
                mngConfig = auth.inMemoryAuthentication();

        UserDetails u1 = User.withUsername("admin").password(cryptPassword).roles("ADMIN").build();
        UserDetails u2 = User.withUsername("user").password(cryptPassword).roles("USER").build();

        mngConfig.withUser(u1);
        mngConfig.withUser(u2);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthFilter(), UsernamePasswordAuthenticationFilter.class)
        ;
    }
}

