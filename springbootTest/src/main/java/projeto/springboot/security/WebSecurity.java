package projeto.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {
	
	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.authorizeHttpRequests( 
	    		authorizeConfig->{
	    			
	    			authorizeConfig.requestMatchers("/").hasAnyRole("ADMIN","USER","SECRETARIA");
	    			authorizeConfig.requestMatchers("/cadastropessoa").hasAnyRole("ADMIN");
	    			authorizeConfig.requestMatchers("/materialize/**").permitAll();
	    			authorizeConfig.requestMatchers("/logout").permitAll();
	    			authorizeConfig.anyRequest().authenticated();
	    		}
	     )
			   .formLogin()
				  .loginPage("/login")
				  .defaultSuccessUrl("/cadastropessoa")
				  .failureUrl("/login?error=true")
				  .permitAll()
				  .and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				.deleteCookies("JSESSIONID");
				return http.build();
	}
	@Bean
	PasswordEncoder getPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	/*
	@Bean
	 UserDetailsService users() {
		UserDetails user = User.builder()
			.username("user")
			.password(getPasswordEncoder().encode("1234"))
			.roles("USER")
			.build();
		UserDetails admin = User.builder()
			.username("admin")
			.password(getPasswordEncoder().encode("12345"))
			.roles("USER", "ADMIN")
			.build();
		return new InMemoryUserDetailsManager(user, admin);
	}
	*/

}
