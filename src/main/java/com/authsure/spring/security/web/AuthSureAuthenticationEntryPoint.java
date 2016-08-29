package com.authsure.spring.security.web;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Used by the <code>ExceptionTranslationFilter</code> to commence authentication via AuthSure.
 * <p>
 *     The user's browser will be redirected to AuthSure for login. This page is specified by the
 *     <code>flowUrl</code> property. Once login is complete, AuthSure will redirect the user's
 *     browser to the configured success or failure URL as configured for the flow in AuthSure.
 * </p>
 *
 * @author Erik R. Jensen
 */
public class AuthSureAuthenticationEntryPoint implements AuthenticationEntryPoint, InitializingBean {

	protected String flowUrl;

	/**
	 * Initializes the AuthSureAuthenticationEntryPoint after the BeanFactory has set all the properties.
	 *
	 * @throws Exception if a misconfiguration occurs
	 */
	public void afterPropertiesSet() throws Exception { // From InitializingBean
		Assert.notNull(this.flowUrl, "the flowUrl must be specified");
	}

	/**
	 * Starts the AuthSure authentication scheme.
	 *
	 * @param request the HTTP request
	 * @param response the HTTP response
	 * @param e the authentication exception that cuased the invocation
	 * @throws IOException if an I/O error occurs
	 * @throws ServletException of an error occurs
	 */
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {
		response.sendRedirect(flowUrl);
	}
}
