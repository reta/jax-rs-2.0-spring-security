package com.example.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class UserDetailsSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService( userDetailsService() );
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername( final String username ) throws UsernameNotFoundException {
                if( username.equals( "admin" ) ) {
                    return new User( username, "password", true, true, true, true,
                        Arrays.asList(
                            new SimpleGrantedAuthority( "ROLE_USER" ),
                            new SimpleGrantedAuthority( "ROLE_ADMIN" )
                        )
                    );
                } else if ( username.equals( "user" ) ) {
                    return new User( username, "password", true, true, true, true,
                        Arrays.asList(
                            new SimpleGrantedAuthority( "ROLE_USER" )
                        )
                    );
                } 
                    
                return null;
            }
        };
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http        
            .httpBasic().and()
            .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ).and()
            .authorizeRequests().antMatchers("/**").hasRole( "USER" );
    }
}