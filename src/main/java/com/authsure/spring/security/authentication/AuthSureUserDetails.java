package com.authsure.spring.security.authentication;

import com.authsure.client.AuthSureLogin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Erik R. Jensen
 */
public class AuthSureUserDetails implements UserDetails {

	protected AuthSureLogin login;

	public AuthSureUserDetails(AuthSureLogin login) {
		this.login = login;
	}

	public AuthSureLogin getLogin() {
		return login;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<String> perms = login.getIdentity().getEffectivePermissions();
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(perms.size());
		for (String perm : perms) {
			grantedAuthorities.add(new SimpleGrantedAuthority(perm));
		}
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return login.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return !login.isExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
