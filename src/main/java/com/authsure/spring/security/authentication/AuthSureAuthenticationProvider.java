package com.authsure.spring.security.authentication;

import com.authsure.client.AuthSureClient;
import com.authsure.client.AuthSureLogin;
import com.authsure.spring.security.web.AuthSureAuthenticationFilter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Processes an AuthSureAuthenticationToken.
 *
 * @author Erik R. Jensen
 */
public class AuthSureAuthenticationProvider implements AuthenticationProvider,
		InitializingBean, MessageSourceAware {

	protected AuthSureClient client;
	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	public AuthSureAuthenticationProvider(AuthSureClient client) {
		this.client = client;
	}

	/**
	 * Initializes the AuthSureAuthenticationProvider after the BeanFactory has set all the properties.
	 *
	 * @throws Exception if a misconfiguration occurs
	 */
	public void afterPropertiesSet() throws Exception {
		// TODO If we don't need me, remove me and kill InitializingBean inheritance
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messages = new MessageSourceAccessor(messageSource);
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		// New authentication
		if (authentication instanceof UsernamePasswordAuthenticationToken
				&& AuthSureAuthenticationFilter.AS_IDENTIFIER.equals(authentication.getPrincipal().toString())) {
			String token = authentication.getCredentials().toString();
			if (StringUtils.hasText(token)) {
				try {
					AuthSureLogin login = client.validateLogin(token);
					return new AuthSureAuthenticationToken(login);
				} catch (IOException x) {
					throw new BadCredentialsException("Token validation failed: " + x.getMessage(), x);
				}
			}
		}

		// Existing authentication
		if (authentication instanceof AuthSureAuthenticationToken) {
			AuthSureAuthenticationToken token = (AuthSureAuthenticationToken)authentication;
			if (token.isExpired()) {
				throw new BadCredentialsException(messages.getMessage(
						"AuthSureAuthenticationProvider.expired",
						"The AuthSure session has expired"));
			}
			return authentication;
		}

		return null;
	}

	public boolean supports(final Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication))
				|| (AuthSureAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
