package com.authsure.spring.security.web;

import com.authsure.client.AuthSureClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.StringUtils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Redirects the user to AuthSure after a successful logout to invalidate the user's session.
 *
 * @author Erik R. Jensen
 */
public class AuthSureLogoutSuccessHandler implements LogoutSuccessHandler {

  protected AuthSureClient client;
  protected String flow;

  public AuthSureLogoutSuccessHandler(AuthSureClient client) {
    this.client = client;
  }

  public AuthSureLogoutSuccessHandler(AuthSureClient client, String flow) {
    this(client);
    this.flow = flow;
  }

  @Override
  public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth)
      throws IOException, ServletException {
    res.sendRedirect(client.getUrl() + "/" + (StringUtils.hasText(flow) ? flow + "/" : "")
        + "logout");
  }
}
