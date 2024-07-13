package org.duccao.licensingservice.services.clients;

import org.duccao.licensingservice.models.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("organization-service")
public interface OrganizationFeignClient {

  @GetMapping("/api/v1/organizations/{id}")
  Organization getOrganization(@PathVariable(name = "id") String organizationId);
}
