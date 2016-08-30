package com.authsure.spring.security.authentication;

import com.authsure.client.AuthSureLogin;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Represents a successful AuthSure <code>Authentication</code>.
 *
 * @author Erik R. Jensen
 */
public class AuthSureAuthenticationToken extends AbstractAuthenticationToken implements Serializable {

	protected AuthSureLogin login;

	protected static List<GrantedAuthority> getAuthorities(AuthSureLogin login) {
		List<String> perms = login.getIdentity().getEffectivePermissions();
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(perms.size());
		for (String perm : perms) {
			grantedAuthorities.add(new SimpleGrantedAuthority(perm));
		}
		return grantedAuthorities;
	}

	public AuthSureAuthenticationToken(AuthSureLogin login) {
		super(getAuthorities(login));
		this.login = login;
	}

	public boolean isExpired() {
		return login.isExpired();
	}

	public Object getCredentials() {
		return null;
	}

	public Object getPrincipal() {
		return login;
	}
}
