package projeto.integrador.equipe1.carrosluxo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import projeto.integrador.equipe1.carrosluxo.Service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserService userService;

    AuthenticationManager authenticationManager;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService);
        authenticationManager = authenticationManagerBuilder.build();
        http
                .csrf().disable()
                .exceptionHandling()
                .and()
                .authenticationManager(authenticationManager)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api-docs/**").permitAll()
                            .requestMatchers(HttpMethod.POST,"/auth").permitAll()
                            .requestMatchers(HttpMethod.POST, "/register").permitAll()
                            .requestMatchers(HttpMethod.GET, "/category").permitAll()
                            .requestMatchers(HttpMethod.GET, "/car").permitAll()
                            .requestMatchers(HttpMethod.GET, "/category/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/car/**").permitAll()
                            .requestMatchers("/category/**", "/car/**").hasRole("ADMIN")
                            .anyRequest().authenticated()
                );
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
