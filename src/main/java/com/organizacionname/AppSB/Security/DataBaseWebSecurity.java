package com.organizacionname.AppSB.Security;

import javax.sql.DataSource;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.*;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class DataBaseWebSecurity {

    /*
     * @Bean
     * UserDetailsManager users(DataSource dataSource){
     * JdbcUserDetailsManager jdbc = new JdbcUserDetailsManager(dataSource);
     * return jdbc;
     * }
     */
    @Bean
    public UserDetailsManager usersCustom(DataSource dataSource ){
        JdbcUserDetailsManager users = new JdbcUserDetailsManager (dataSource);
        users.setUsersByUsernameQuery("select username, password, estatus from Usuarios where username=?") ;
        users.setAuthoritiesByUsernameQuery("select u.username, p.perfil from UsuarioPerfil up " +
                                            "inner join Usuarios u on u.id = up.idUsuario " +
                                            "inner join Perfiles p on p.id = up.idPerfil " + "where u.username = ?");
        return users;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authorize -> authorize
            //Los recursos estáticos no requieren autenticación
            .requestMatchers("/bootstrap/**","/images/**","/tinymce/**","/logos/**").permitAll()
            //Las vistas públicas no requieren autenticación
            .requestMatchers ("/","/login", "/signup","/search","/bcrypt/**","/vacantes/view/**").permitAll()
            // Asignar permisos a URLs por ROLES
            .requestMatchers("/solicitudes/create/**","/solicitudes/save/**").hasAnyAuthority("Usuario")
            .requestMatchers("/solicitudes/**").hasAnyAuthority("Supervisor","Administrador")
            .requestMatchers("/vacantes/**").hasAnyAuthority("Supervisor","Administrador")
            .requestMatchers("/categorias/**").hasAnyAuthority ("Supervisor","Administrador")
            .requestMatchers("/usuarios/**").hasAnyAuthority("Administrador")
            //Todas las demás URLs de la Aplicación requieren autenticación
            .anyRequest().authenticated());

            //El formulario de Login no requiere autenticacion
        //http.formLogin(form -> form.permitAll()); // este login es por defecto
        http.formLogin(form -> form.loginPage("/login").permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder  encoder(){
        return new BCryptPasswordEncoder ();
    }
    
}
