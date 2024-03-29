package lv.venta.confs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.DispatcherType;
import lv.venta.services.impl.security.MyUserDetailsManagerImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/"));
  }

  //norošināt lietotajus un to lomas
  //userDetailsManager
  /* @Bean
  public InMemoryUserDetailsManager userDetailsManager() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withDefaultPasswordEncoder()
    .username("alina.rubcova").password("123").authorities("ADMIN").build());
    manager.createUser(User.withDefaultPasswordEncoder()
    .username("janis.berzins").password("321").authorities("USER").build());
    manager.createUser(User.withDefaultPasswordEncoder()
    .username("liga.jauka").password("987").authorities("USER", "ADMIN").build());
  
    return manager;
  }
  */
  
  @Bean
  public MyUserDetailsManagerImpl userDetailsManager() {
    MyUserDetailsManagerImpl manager = new MyUserDetailsManagerImpl();
    return manager;
  }
  
  @Bean 
  public PasswordEncoder passwordEncoderSimple2() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
  
  @Bean
  public AuthenticationManager authManager(HttpSecurity http) throws Exception {
  AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
  
  
  authenticationManagerBuilder.userDetailsService(userDetailsManager()).passwordEncoder(passwordEncoderSimple2());
  return authenticationManagerBuilder.build();
  }
  
  
  //nodorošināt piekļuvi konkrētiem endpointien
  //SecurityFilterChain
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    http.authorizeHttpRequests()
    .requestMatchers("/hello").permitAll()
    .requestMatchers("/msg").permitAll()
    .requestMatchers("/product").hasAnyAuthority("ADMIN")
    .requestMatchers("/productOne").hasAnyAuthority("ADMIN")
    .requestMatchers("/product/").hasAnyAuthority("ADMIN", "USER")
    .requestMatchers("/allproducts").permitAll()
    .requestMatchers("/allproducts/").permitAll()
    .requestMatchers("/insert").hasAnyAuthority("ADMIN")
    .requestMatchers("/update/").hasAnyAuthority("ADMIN")
    .requestMatchers("/delete/").hasAnyAuthority("ADMIN")
    .requestMatchers("/filter/quantity/").permitAll()
    .requestMatchers("/error").permitAll()
    .requestMatchers("/h2-console").permitAll()
    .requestMatchers("/h2-console/**").permitAll()
    .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
    .and()
    .formLogin().permitAll()
    .and()
    .logout().permitAll()
    .and().exceptionHandling().accessDeniedPage("/my_access_denied");
    
    
    return http.build();

  }

}