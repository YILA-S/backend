package backend.security;

import backend.security.filter.CustomAuthenticationFilter;
import backend.security.filter.CustomAuthorizationFilter;
import backend.security.providers.ManagerAuthenticationProvider;
import backend.security.providers.StudentAuthenticationProvider;
import backend.security.providers.TeacherAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Autowired
    private StudentAuthenticationProvider studentAuthenticationProvider;
    @Autowired
    private TeacherAuthenticationProvider teacherAuthenticationProvider;

    @Autowired
    private ManagerAuthenticationProvider managerAuthenticationProvider;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/", "/token/refresh/**", "/manager/**").permitAll();
        http.authorizeRequests().antMatchers( "/student/**").hasAnyAuthority("ADMIN","TEACHER");
        http.authorizeRequests().antMatchers( "/coursePeriod/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers( POST,"/course/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers( DELETE,"/course/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers( GET,"/course/**").hasAnyAuthority("ADMIN", "TEACHER");
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().antMatchers());
    }


    @Bean
    public ProviderManager authenticationManagerBean() throws Exception {
        ProviderManager providerManager = new ProviderManager(studentAuthenticationProvider,
                teacherAuthenticationProvider, managerAuthenticationProvider);
        return providerManager;
    }
}
