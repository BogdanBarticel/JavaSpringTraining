package spring.tutorial.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password("secret1").roles("USER")
                .and()
                .withUser("user2").password("secret2").roles("USER");
    }

    // servicii -> roles
    // roles
    // user
    // table user
    // table user roles
    // url -> fetch roles that can access it -> compare with roles of users
    // Customer Admin
    // Admin nu poate sa comande produse


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/service/**").fullyAuthenticated();
        http.httpBasic();
        http.csrf().disable();
    }
}