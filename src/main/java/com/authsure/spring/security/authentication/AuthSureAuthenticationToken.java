package com.authsure.spring.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Represents a successful AuthSure <code>Authentication</code>.
 *
 * @author Erik R. Jensen
 */
public class AuthSureAuthenticationToken extends AbstractAuthenticationToken implements Serializable {

	private Date expiration;

	public boolean isExpired() {
		return expiration != null && expiration.before(new Date());
	}

	public AuthSureAuthenticationToken(final Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		// TODO Implement me!
	}

	public Object getCredentials() {
		// TODO Implement me!
		return null;
	}

	public Object getPrincipal() {
		// TODO Implement me!
		return null;
	}
}