package com.proyecto.sistemagestion.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT correo, password, estado FROM empleado WHERE correo=?")
                .authoritiesByUsernameQuery("SELECT correo, rol FROM empleado WHERE correo=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/empresas/**", "/nuevaempresa/**",
                        "/guardarempresa/**", "/editarempresa/**",
                        "/actualizarempresa/**", "/eliminarempresa/**",
                        "/usuarios/**", "/nuevousuario/**",
                        "/guardarusuario/**", "/editarusuario/**",
                        "/actualizarusuario/**", "/eliminarusuario/**",
                        "/editarmovimiento/**", "/actualizarmovimiento/**",
                        "/eliminarmovimiento/**").hasRole("ADMIN")
                .antMatchers("/", "/movimientos/**",
                        "/nuevomovimiento/**", "/guardarmovimiento/**").hasAnyRole("ADMIN", "USER")
                .and().formLogin().loginPage("/login")
                .and().exceptionHandling().accessDeniedPage("/denied")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }

}
