package com.enterprise.patito.inventory.security;

import com.enterprise.patito.manager.token.security.JwtTokenFilter;
import com.enterprise.patito.manager.token.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors() // Habilitar la configuración de CORS
                .and()
                .authorizeRequests()
                .antMatchers("/v1/products/**").authenticated()
                .antMatchers("/v1/stock/**").authenticated()
                .antMatchers("/v1/warehouses/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

}
