    package com.example.banking.security;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    public class SecurityConfig {

        private final JwtAuthenticationFilter jwtFilter;


        public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
            this.jwtFilter = jwtFilter;
        }


        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
            return configuration.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
            .cors(Customizer.withDefaults())
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/auth/**").permitAll()// Allow all APIs for testing
                            .requestMatchers("/user/**").authenticated()
                            .anyRequest().authenticated()) // Disable authentication
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }







    }
