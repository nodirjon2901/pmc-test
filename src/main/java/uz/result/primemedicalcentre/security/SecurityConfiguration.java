package uz.result.primemedicalcentre.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;

    private final AuthService authService;

    private final CustomAccessDeniedHandler accessDeniedHandler;

    private final CustomAuthenticationEntryPoint entryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationRequest -> {
                    authorizationRequest
                            .requestMatchers(
                                    "/**"
//                                    getMatchers()
                            )
                            .permitAll()
                            .anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .userDetailsService(authService)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(customizer ->
                        customizer
                                .accessDeniedHandler(accessDeniedHandler)
                                .authenticationEntryPoint(entryPoint));
        return http.build();
    }

    private RequestMatcher[] getMatchers() {
        return new RequestMatcher[]{
                //Auth
                new AntPathRequestMatcher("/v1/auth/login", "POST"),
                //All-GET
                new AntPathRequestMatcher("/v1/**", "GET"),
                //Application
                new AntPathRequestMatcher("/v1/application/**", "POST"),
                //Telegram-bot
                new AntPathRequestMatcher("/v1/counter/**", "POST"),
                //Error
                new AntPathRequestMatcher("/error"),
                //Swagger
                new AntPathRequestMatcher("/api/swagger-ui/**"),
                new AntPathRequestMatcher("/swagger-ui/**"),
                new AntPathRequestMatcher("/v3/api-docs/**"),
                new AntPathRequestMatcher("/webjars/**"),
                new AntPathRequestMatcher("/swagger-resources/**"),
                new AntPathRequestMatcher("/configuration/**"),
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
