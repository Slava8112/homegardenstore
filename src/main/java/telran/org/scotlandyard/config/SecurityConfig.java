//package telran.org.scotlandyard.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import telran.org.scotlandyard.security.JwtAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Bean
//     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http
//                    .csrf(AbstractHttpConfigurer::disable)
//                    .authorizeHttpRequests(request -> request
//                            //.requestMatchers(HttpMethod.POST, "/users").permitAll()
//                            .anyRequest().permitAll())
//                            //authenticated())
//                    .httpBasic(Customizer.withDefaults())
//                    .sessionManagement(session -> session
//                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                    .addFilterBefore(jwtAuthenticationFilter,
//                            UsernamePasswordAuthenticationFilter.class);
//
//            return http.build();
//        }
//}
