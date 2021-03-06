package com.projetopi.tlgne.config;

import com.projetopi.tlgne.repositories.UsuarioRepository;
import com.projetopi.tlgne.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), usuarioRepository))
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/produtos", "/produtos/**").permitAll()
                .antMatchers(HttpMethod.POST, "/produtos", "/produtos/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/produtos", "/produtos/**").hasAnyRole("ADMIN", "ESTOQUISTA")
                .antMatchers(HttpMethod.DELETE, "/produtos", "/produtos/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/imagens", "/imagens/**").permitAll()
                .antMatchers(HttpMethod.POST, "/imagens", "/imagens/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/imagens", "/imagens/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/imagens", "/imagens/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/usuarios").permitAll()
                .antMatchers(HttpMethod.GET, "/funcionarios", "/funcionarios/**").permitAll()//hasAnyRole("ADMIN", "ESTOQUISTA")
                .antMatchers(HttpMethod.POST, "/funcionarios", "/funcionarios/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/vendas", "/vendas/**").hasAnyRole("ADMIN", "ESTOQUISTA")
                .antMatchers("/enderecos", "/enderecos/**").permitAll()
                .antMatchers("/vendas", "/vendas/**").permitAll()
                .antMatchers("/clientes", "/**").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login");

    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
