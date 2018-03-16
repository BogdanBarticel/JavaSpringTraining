package spring.tutorial.configurator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import spring.tutorial.security.ShopUserDetailsService;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = ShopUserDetailsService.class)
public class SecurityConfigurator extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.anonymous().disable().headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers("/export/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/create").hasAuthority("ROLE_CUSTOMER")
                .antMatchers("/browse").hasAuthority("ROLE_CUSTOMER")
                .and()
                .formLogin().loginPage("/login").failureUrl("/login-error")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/login-error");
        http.csrf().disable();
    }
}