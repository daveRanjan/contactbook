package com.centralway.contactbook.security.config;

import com.centralway.contactbook.security.CustomCorsFilter;
import com.centralway.contactbook.security.RestAuthenticationEntryPoint;
import com.centralway.contactbook.security.auth.jwt.JwtAuthenticationProvider;
import com.centralway.contactbook.security.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import com.centralway.contactbook.security.auth.jwt.SkipPathRequestMatcher;
import com.centralway.contactbook.security.auth.jwt.extractor.TokenExtractor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
    public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/user/login";
    public static final String FORM_BASED_REGISTER_ENTRY_POINT = "/user/register";
    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/**";

    @Autowired private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired private AuthenticationFailureHandler failureHandler;
    @Autowired private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired private TokenExtractor tokenExtractor;

    @Autowired private AuthenticationManager authenticationManager;

    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {
        List<String> pathsToSkip = Arrays.asList(FORM_BASED_REGISTER_ENTRY_POINT, FORM_BASED_LOGIN_ENTRY_POINT);
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, TOKEN_BASED_AUTH_ENTRY_POINT);
        JwtTokenAuthenticationProcessingFilter filter = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable() // We don't need CSRF for JWT based authentication
            .exceptionHandling()
            .authenticationEntryPoint(this.authenticationEntryPoint)

            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()
            .antMatchers(FORM_BASED_LOGIN_ENTRY_POINT)
            .permitAll() // Login end-point

            .and()
            .authorizeRequests()
            .antMatchers(FORM_BASED_REGISTER_ENTRY_POINT)
            .permitAll() // Login end-point

            .and()
            .authorizeRequests()
            .antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT)
            .authenticated() // Protected API End-points
            .and()
            .addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
