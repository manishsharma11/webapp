package com.main.sts.common;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().disable();
        
//      http
//      .csrf().disable()
//      .authorizeRequests()
//        .antMatchers(HttpMethod.POST, "/sts/**").authenticated()
//        .antMatchers(HttpMethod.PUT, "/sts/**").authenticated()
//        .antMatchers(HttpMethod.DELETE, "/sts/**").authenticated()
//        .anyRequest().permitAll()
//        .and()
//      .httpBasic().and()
//      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    
    
//    @Configuration
//    public class SecurityConfig extends WebSecurityConfigurerAdapter {
//      @Override
//      protected void configure(HttpSecurity http) throws Exception {
//        http
//          .csrf().disable()
//          .authorizeRequests()
//            .antMatchers(HttpMethod.POST, "/api/**").authenticated()
//            .antMatchers(HttpMethod.PUT, "/api/**").authenticated()
//            .antMatchers(HttpMethod.DELETE, "/api/**").authenticated()
//            .anyRequest().permitAll()
//            .and()
//          .httpBasic().and()
//          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//      }
//    }
}