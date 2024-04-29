package fr.fms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity		
//@EnableGlobalMethodSecurity(prePostEnabled = true)	permet d'activer la sécurité au niveau des méthodes qu'il faudra décorer ainsi pour activer @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class SecurityConfig extends WebSecurityConfigurerAdapter {	
	@Autowired (required = true)
	DataSource dataSource;	
	
	@Override  
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)			
			.usersByUsernameQuery("select username as principal, password as credentials, active from T_Users where username=?")
			.authoritiesByUsernameQuery("select username as principal, role as role from T_Users_Roles where username=?")
			.rolePrefix("ROLE_")		
			.passwordEncoder(passwordEncoder());
	}
	
	@Bean	
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		  http.formLogin().loginPage("/login");
		  http.authorizeHttpRequests().antMatchers("/confirm","/porder","/order","/save","/delete","/edit","/article").hasRole("ADMIN");
		  http.authorizeHttpRequests().antMatchers("/confirm","/porder","/order").hasRole("USER"); 
		  http.exceptionHandling().accessDeniedPage("/403");		 		
		  
		  /* Dans cette gestion de la sécurité en lien avec LoginController, il faut comprendre que :
		   * tout accès vers une URL non autorisée d'un utilisateur non connecté renverra vers le formulaire login
		   * tout accès vers une URL d'un USER vers une ressource ADMIN renverra vers une 403
		   * tout accès vers une URL inexistante renverra vers une 404 */
	  }
}


/*
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.method.configuration.
 * EnableGlobalMethodSecurity; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; import
 * org.springframework.security.core.userdetails.User; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.security.provisioning.InMemoryUserDetailsManager; import
 * org.springframework.security.web.SecurityFilterChain;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import javax.sql.DataSource;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityConfig {
 * 
 * @Autowired DataSource dataSource;
 * 
 * @Bean protected InMemoryUserDetailsManager configureAuthentication() {
 * List<UserDetails> userDetails = new ArrayList<>();
 * 
 * List<GrantedAuthority> adminRoles = new ArrayList<>(); adminRoles.add(new
 * SimpleGrantedAuthority("ADMIN")); userDetails.add(new
 * User("admin","$2a$12$A.1omyeduJjn9BulU5TVxuLmvfC6FFiqUQieW2Y8Nc2xGwr44p5N2",
 * adminRoles));
 * 
 * List<GrantedAuthority> userRoles = new ArrayList<>(); userRoles.add(new
 * SimpleGrantedAuthority("USER")); userDetails.add(new
 * User("user","$2a$12$A.1omyeduJjn9BulU5TVxuLmvfC6FFiqUQieW2Y8Nc2xGwr44p5N2",
 * userRoles));
 * 
 * return new InMemoryUserDetailsManager(userDetails); }
 * 
 * @Bean PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder();
 * }
 * 
 * @Bean protected SecurityFilterChain filterChain(HttpSecurity http) throws
 * Exception { http.formLogin().loginPage("/login");
 * http.authorizeRequests().antMatchers("/confirm","/porder","/order","/save",
 * "/delete","/edit","/article").hasAuthority("ADMIN");
 * http.authorizeRequests().antMatchers("/confirm","/porder","/order").
 * hasAnyAuthority("USER","ADMIN");
 * 
 * http.exceptionHandling().accessDeniedPage("/403"); return http.build(); } }
 */