package than.MK.weblaptop.backend.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import than.MK.weblaptop.backend.fillter.JwtFillter;
import than.MK.weblaptop.backend.service.UserServiceInterface;

import java.util.Arrays;

@Configuration
public class SecurityConfiguration {
    @Autowired
    private JwtFillter jwtFillter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Autowired
    public DaoAuthenticationProvider authenticationProvider(UserServiceInterface userServiceInterface) {
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setUserDetailsService(userServiceInterface);
        dap.setPasswordEncoder(passwordEncoder());
        return dap;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.authorizeHttpRequests(
                config -> config
                        .requestMatchers(HttpMethod.POST, Endpoints.PUBLIC_POST_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.GET, Endpoints.PUBLIC_GET_ENDPOINT).permitAll()
                        .requestMatchers(HttpMethod.POST, Endpoints.ADMIN_POST_ENDPOINT).hasRole("Admin")
                        .requestMatchers(HttpMethod.GET, Endpoints.ADMIN_GET_ENDPOINT).hasRole("Admin")
        );
        httpSecurity.cors(cors -> {
            cors.configurationSource(request -> {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.addAllowedOrigin(Endpoints.FRONT_END_HOST);
                corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                corsConfiguration.addAllowedHeader("*");
                return corsConfiguration;
            });
        });

        httpSecurity.addFilterBefore(jwtFillter, UsernamePasswordAuthenticationFilter.class);
        //Không tạo session vì đã sử dụng token để xác thực biết request của ai trên server
        httpSecurity.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(csrf -> csrf.disable());
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
