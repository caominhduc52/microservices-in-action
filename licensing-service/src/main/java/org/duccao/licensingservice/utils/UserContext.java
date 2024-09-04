package org.duccao.licensingservice.utils;

import org.springframework.stereotype.Component;

/**
 * Description of the class goes here.
 *
 * @author Duc Cao
 * @version 1.0
 * @since 9/3/2024
 */
@Component
public class UserContext {

  public static final String CORRELATION_ID = "x-correlation-id";
  public static final String USER_ID = "x-user-id";
  public static final String AUTH_TOKEN = "x-auth-token";
  public static final String ORGANIZATION_ID = "x-organization-id";

  private String correlationId;
  private String userId;
  private String authToken;
  private String organizationId;

  public String getCorrelationId() {
    return correlationId;
  }

  public void setCorrelationId(String correlationId) {
    this.correlationId = correlationId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  public String getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(String organizationId) {
    this.organizationId = organizationId;
  }
}
