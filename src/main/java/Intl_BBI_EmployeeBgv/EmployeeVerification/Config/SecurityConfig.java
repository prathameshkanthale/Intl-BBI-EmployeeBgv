//package Intl_BBI_EmployeeBgv.EmployeeVerification.Config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//        @Bean
//        public PasswordEncoder passwordEncoder() {
//            return new BCryptPasswordEncoder();
//        }
//        
//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http
//                .csrf(csrf -> csrf.disable())  // Disable CSRF for testing
//                .authorizeHttpRequests(auth -> auth
//                    .anyRequest().permitAll()  // Allow all requests
//                )
//                .httpBasic(); // Keep basic auth enabled, but it's not required for now
//
//            return http.build();
//        }
//
//}