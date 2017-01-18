package com.authsure.spring.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.io.Serializable;

/**
 * Represents a successful AuthSure <code>Authentication</code>.
 *
 * @author Erik R. Jensen
 */
public class AuthSureAuthenticationToken extends AbstractAuthenticationToken implements Serializable {

	protected AuthSureUserDetails userDetails;

	public AuthSureAuthenticationToken(AuthSureUserDetails userDetails) {
		super(userDetails.getAuthorities());
		this.userDetails = userDetails;
	}

	@Override
	public Object getCredentials() {
		return userDetails.getLogin();
	}

	@Override
	public Object getPrincipal() {
		return userDetails;
	}

	public boolean isExpired() {
		return userDetails.getLogin().isExpired();
	}
}
