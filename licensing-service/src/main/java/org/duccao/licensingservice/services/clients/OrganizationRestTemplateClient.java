package org.duccao.licensingservice.services.clients;

import org.duccao.licensingservice.models.Organization;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Description of the class goes here.
 *
 * @author Duc Cao
 * @version 1.0
 * @since 7/13/2024
 */

@Component
public class OrganizationRestTemplateClient {

  private final RestTemplate restTemplate;

  public OrganizationRestTemplateClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public Organization getOrganization(String id) {
    ResponseEntity<Organization> restExchange = restTemplate.exchange(
      "http://organization-service/api/v1/organizations/{organizationId}",
      HttpMethod.GET, null, Organization.class, id);

    return restExchange.getBody();
  }
}
