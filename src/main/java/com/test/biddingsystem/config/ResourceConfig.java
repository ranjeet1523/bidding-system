package com.test.biddingsystem.config;


import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Profile("prod")
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/h2-console", "/oauth/token", "/oauth/authorize**",
                "/bidding_system/v1/auction**").permitAll();

        http.requestMatchers().antMatchers("/bidding_system/v1/place/**")
                .and().authorizeRequests()
                .antMatchers("/bidding_system/v1/place/**").access("hasRole('USER')")
                .and().requestMatchers().antMatchers("/admin")
                .and().authorizeRequests()
                .antMatchers("/admin").access("hasRole('ADMIN')");
    }
}
