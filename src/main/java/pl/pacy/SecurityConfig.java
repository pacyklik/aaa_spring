package pl.pacy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by pacy on 10.09.16.
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	class CsrfHeaderFilter extends OncePerRequestFilter {

		@Override
		protected void doFilterInternal(HttpServletRequest request,
				HttpServletResponse response, FilterChain filterChain)
				throws ServletException, IOException {
			CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
			if (csrf != null) {
				Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
				String token = csrf.getToken();
				if (cookie == null || token != null && !token.equals(cookie.getValue())) {
					cookie = new Cookie("XSRF-TOKEN", token);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
			filterChain.doFilter(request, response);
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// przyklad autoryzacji z basic
		http
				.httpBasic().and()
				.authorizeRequests()
				.antMatchers("/*.html", "/favicon.ico", "/", "/js/*", "/css/*").permitAll().anyRequest()
				.authenticated()
				.and().logout();
		//.and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);

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