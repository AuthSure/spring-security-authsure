package com.authsure.spring.security.web;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Erik R. Jensen
 */
public class AuthSureAuthenticationEntryPoint implements AuthenticationEntryPoint, InitializingBean {

	public void afterPropertiesSet() throws Exception {
		// TODO Implement me!
	}

	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {
		// TODO Implement me!
	}
}
