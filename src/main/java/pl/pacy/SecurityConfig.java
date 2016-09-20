package pl.pacy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by pacy on 10.09.16.
 */

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// przyklad autoryzacji z basic
		http
				.httpBasic().and()
				.authorizeRequests()
				.antMatchers("/index.html", "/home.html", "/login.html", "/favicon.ico", "/").permitAll().anyRequest()
				.authenticated()
				.and().logout();
		//				.and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);

		// dezaktywacja tokena csrf
		http.csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("a").password("a")
				.roles("ADMIN", "USER").and().withUser("user").password("user")
				.roles("USER");
	}
}