package com.authsure.spring.security.web;

import com.authsure.client.AuthSureClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Used by the <code>ExceptionTranslationFilter</code> to commence authentication via AuthSure.
 * <p>
 * The user's browser will be redirected to AuthSure for login. This page is specified by the
 * <code>flowUrl</code> property. Once login is complete, AuthSure will redirect the user's
 * browser to the configured success or failure URL as configured for the flow in AuthSure.
 * </p>
 *
 * @author Erik R. Jensen
 */
public class AuthSureAuthenticationEntryPoint
    implements AuthenticationEntryPoint, InitializingBean {

  protected String flow;
  protected AuthSureClient client;

  /**
   * Creates a new AuthSureAuthenticationEntryPoint assuming the default authentication flow.
   *
   * @param client the AuthSureClient
   */
  public AuthSureAuthenticationEntryPoint(AuthSureClient client) {
    this.client = client;
  }

  /**
   * Creates a new AuthSureAuthenticationEntryPoint using the given authentication flow.
   *
   * @param client the AuthSureClient
   * @param flow   the authentication flow to use
   */
  public AuthSureAuthenticationEntryPoint(AuthSureClient client, String flow) {
    this(client);
    this.flow = flow;
  }

  /**
   * Initializes the AuthSureAuthenticationEntryPoint after the BeanFactory has set all
   * the properties.
   *
   * @throws Exception if a misconfiguration occurs
   */
  public void afterPropertiesSet() throws Exception { // From InitializingBean
    Assert.notNull(this.client, "the AuthSure client must be provided");
  }

  /**
   * Starts the AuthSure authentication scheme.
   *
   * @param request  the HTTP request
   * @param response the HTTP response
   * @param e        the authentication exception that caused the invocation
   * @throws IOException      if an I/O error occurs
   * @throws ServletException of an error occurs
   */
  public void commence(HttpServletRequest request, HttpServletResponse response,
                       AuthenticationException e)
      throws IOException, ServletException {
    response.sendRedirect(client.getUrl() + "/" + (StringUtils.hasText(flow) ? flow : ""));
  }
}
