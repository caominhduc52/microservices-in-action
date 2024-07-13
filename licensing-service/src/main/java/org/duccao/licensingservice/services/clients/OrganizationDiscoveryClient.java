package org.duccao.licensingservice.services.clients;

import java.util.List;
import org.duccao.licensingservice.models.Organization;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
public class OrganizationDiscoveryClient {

  private final DiscoveryClient discoveryClient;

  public OrganizationDiscoveryClient(DiscoveryClient discoveryClient) {
    this.discoveryClient = discoveryClient;
  }

  public Organization getOrganization(String organizationId) {
    RestTemplate restTemplate = new RestTemplate();
    List<ServiceInstance> instances = discoveryClient.getInstances("organization-service");

    if (instances.isEmpty()) {
      return null;
    }

    String serviceUri =
      String.format("%s/api/v1/organizations/%s", instances.getFirst().getUri().toString(), organizationId);

    ResponseEntity<Organization> restExchange =
      restTemplate.exchange(serviceUri, HttpMethod.GET, null, Organization.class, organizationId);

    return restExchange.getBody();
  }
}
