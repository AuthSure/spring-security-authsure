package com.authsure.spring.security.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Erik R. Jensen
 */
public class AuthSureAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public AuthSureAuthenticationFilter() {
		super("/login/authsure");

	}

	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
			throws AuthenticationException, IOException, ServletException {
		// TODO Implement me!
		return null;
	}
}
