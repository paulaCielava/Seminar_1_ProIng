package lv.venta.confs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// nodoršināt lietotājus un to lomas (lietotājvārdi, paroles)
	//userDetailsManager
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		UserDetails user1 = User.withDefaultPasswordEncoder().username("paula.cielava").password("123").authorities("ADMIN").build();
		manager.createUser(user1);
		
		UserDetails user2 = User.withDefaultPasswordEncoder().username("janis.berzins").password("321").authorities("USER").build();
		manager.createUser(user2);
		UserDetails user3 = User.withDefaultPasswordEncoder().username("liga.jauka").password("111").authorities("USER", "ADMIN").build();
		manager.createUser(user3);
		
		return manager;
	}
	
	
	
	// modrošināt piekļuvi konkrētiem endpointiem
	//simpleSecurityChain
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests()
		.requestMatchers("/hello").permitAll()
		.requestMatchers("/msg").permitAll()
		.requestMatchers("/product").hasAnyAuthority("ADMIN")
		.requestMatchers("/productOne").hasAnyAuthority("ADMIN")
		.requestMatchers("/product/**").hasAnyAuthority("ADMIN", "USER")
		.requestMatchers("/allproducts").permitAll()
		.requestMatchers("/allproducts/**").permitAll()
		.requestMatchers("/insert").hasAnyAuthority("ADMIN")
		.requestMatchers("/update/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/delete/**").hasAnyAuthority("ADMIN")
		.requestMatchers("/filter/quantity/**").permitAll()
		.requestMatchers("/error").permitAll()
		.and()
		.formLogin().permitAll()
		.and()
		.logout().permitAll();
		
		return http.build();
		
		
		// 1. izveidot myUser klasi ar 
		//    id, name, surmane, username, password
		// 2. izveidot Authority klasi ar id, title
		// 3. uztaisīt starp abām saistību - manyToMany
		// 4. izveidot klasē ar maind funkciju arī ComandLinerRunner 
		//    funkciju un tajā ievietot testa datus
		// 5. veidosim savu MyUserDetails
		// 6. veidosim savu MyUserDetailsImpl
	}
	
	
}
