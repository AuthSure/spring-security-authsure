package com.authsure.spring.security.web;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Processes AuthSure authentication callbacks.
 *
 * @author Erik R. Jensen
 */
public class AuthSureAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public static final String AS_IDENTIFIER = "__authsure__";

	public AuthSureAuthenticationFilter() {
		super("/login/authsure");
	}

	private String getToken(HttpServletRequest request) {
		String t = request.getParameter("t");
		if (t == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Failed to obtain AuthSure token");
			}
			t = "";
		}
		return t;
	}

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String token = getToken(request);
		assert token != null;
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(AS_IDENTIFIER, token);
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		// TODO Implement me!
		super.successfulAuthentication(request, response, chain, authResult);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		// TODO Implement me!
		super.unsuccessfulAuthentication(request, response, failed);
	}
}
