package com.authsure.spring.security.authentication;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author Erik R. Jensen
 */
public class AuthSureAuthenticationProvider implements AuthenticationProvider,
		InitializingBean, MessageSourceAware {

	public void afterPropertiesSet() throws Exception {
		// TODO Implement me!
	}

	public void setMessageSource(MessageSource messageSource) {
		// TODO Implement me!
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Implement me!
		return null;
	}

	public boolean supports(final Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication))
				|| (AuthSureAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
