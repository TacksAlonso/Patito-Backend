package com.enterprise.patito.orders.security;

import com.enterprise.patito.manager.token.security.RoleBasedAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.enterprise.patito.manager.token.security.JwtTokenProvider;
import com.enterprise.patito.manager.token.security.JwtTokenFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RoleBasedAccessService roleBasedAccessService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/v1/audits/**").authenticated()
                .antMatchers("/v1/stores/**").authenticated()
                .antMatchers("/v1/orders/**").authenticated()
                .antMatchers("/v1/orderitems/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
