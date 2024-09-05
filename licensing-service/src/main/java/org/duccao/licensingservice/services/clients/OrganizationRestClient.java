package org.duccao.licensingservice.services.clients;

import org.duccao.licensingservice.models.Organization;
import org.duccao.licensingservice.utils.UserContext;
import org.duccao.licensingservice.utils.UserContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * Description of the class goes here.
 *
 * @author Duc Cao
 * @version 1.0
 * @since 9/4/2024
 */
@Component
public class OrganizationRestClient {

  private final RestClient.Builder restClientBuilder;

  public OrganizationRestClient(RestClient.Builder restClientBuilder) {
    this.restClientBuilder = restClientBuilder;
  }

  public Organization getOrganization(String id) {
    ResponseEntity<Organization> result = restClientBuilder
      .defaultHeader(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken())
      .build()
      .get()
      .uri("http://organization-service/api/v1/organizations/{organizationId}", id)
      .retrieve()
      .toEntity(Organization.class);

    return result.getBody();
  }
}
