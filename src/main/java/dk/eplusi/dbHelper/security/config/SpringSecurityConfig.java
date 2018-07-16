package dk.eplusi.dbHelper.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import java.util.Arrays;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Value("${http.port}")
//    private int httpPort;
//    @Value("${server.port}")
//    private int httpsPort;
    private final AccessDeniedHandler accessDeniedHandler;

    @Autowired
    public SpringSecurityConfig(AccessDeniedHandler accessDeniedHandler) {
        super();
        this.accessDeniedHandler = accessDeniedHandler;
    }

    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/resources/**", "/css/**", "/fonts/**", "/js/**", "/less/**", "/scss/**", "/images/**", "/webjars/**");
        webSecurity.httpFirewall(allowUrlEncodedSlashHttpFirewall());
    }

    /**
     * customize firewall
     * @return custom firewall
     */
    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowSemicolon(true);
        return firewall;
    }

    // custom custom403.html access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                //port for HTTP / HTTPS
//                .portMapper()
//                .http(httpPort).mapsTo(httpsPort)
//                .and()
                //CSRF setting
//                .csrf()
//                .ignoringAntMatchers("/h2-console/**")
//                .and()
                //authorization for requests
                .authorizeRequests()
                /*.antMatchers("/admin/**").hasAnyRole("ADMIN")*/   //intentionally expose admin page.
//                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //header options
                .headers()
                .frameOptions().disable()
                .and()
//                //SSL
//                .requiresChannel()
//                .antMatchers("/login*").requiresSecure()
//                .anyRequest().requiresInsecure()
//                .and()
                //session
                .sessionManagement()
                .sessionFixation().none()
                .and()
                //login & logout
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                //error handlers
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);

    }

    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}user").roles("USER")
                .and()
                .withUser("admin").password("{noop}admin").roles("ADMIN");
    }
}