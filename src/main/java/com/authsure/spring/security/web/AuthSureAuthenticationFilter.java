package com.authsure.spring.security.web;

import com.authsure.client.AuthSureClient;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

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
	protected static final String ENDPOINT = "/login/authsure";

	protected AuthSureClient client;

	/**
	 * Creates a new AuthSureAuthenticationFilter
	 *
	 * @param client the AuthSureClient
	 */
	public AuthSureAuthenticationFilter(AuthSureClient client) {
		super(ENDPOINT);
		this.client = client;
		this.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(
				client.getUrl() + "/failure"));
	}

	/**
	 * Creates a new AuthSureAuthenticationFilter
	 *
	 * @param client the AuthSureClient
	 * @param flow the flow name
	 */
	public AuthSureAuthenticationFilter(AuthSureClient client, String flow) {
		super(ENDPOINT);
		this.client = client;
		this.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(
				client.getUrl() + "/" + (StringUtils.hasText(flow) ? flow : "") + "/failure"));

	}

	/**
	 * Retrieves the authentication token from the HttpServletRequest.
	 *
	 * @param request the request
	 * @return the token or an empty String, never null
	 */
	protected String getToken(HttpServletRequest request) {
		String t = request.getParameter("t");
		if (t == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Failed to obtain AuthSure token from request");
			}
			t = "";
		}
		return t;
	}

	/**
	 * Attempts authentication by creating an authentication request and passing it to the AuthenticationManager.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the Authentication object from the AuthenticationManager
	 *
	 * @throws AuthenticationException if an error occurs
	 * @throws IOException if an I/O error occurs
	 * @throws ServletException if an error occurrs
	 */
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		Assert.notNull(this.getAuthenticationManager(), "AuthenticationManager may not be null");
		String token = getToken(request);
		assert token != null;
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(AS_IDENTIFIER, token);
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
		return this.getAuthenticationManager().authenticate(authRequest);
	}
}
