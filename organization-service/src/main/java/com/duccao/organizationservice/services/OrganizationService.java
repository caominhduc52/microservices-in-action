package com.duccao.organizationservice.services;

import com.duccao.organizationservice.models.Organization;
import com.duccao.organizationservice.repositories.OrganizationRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Description of the class goes here.
 *
 * @author Duc Cao
 * @version 1.0
 * @since 7/13/2024
 */

@Service
public class OrganizationService {

  private final OrganizationRepository organizationRepository;

  public OrganizationService(OrganizationRepository organizationRepository) {
    this.organizationRepository = organizationRepository;
  }

  public Organization findById(String organizationId) {
    Optional<Organization> organization = organizationRepository.findById(organizationId);
    return (organization.isPresent()) ? organization.get() : null;
  }

  public Organization create(Organization organization) {
    organization.setId(UUID.randomUUID().toString());
    return organizationRepository.save(organization);
  }

  public void update(Organization organization) {
    organizationRepository.save(organization);
  }

  public void delete(Organization organization) {
    organizationRepository.deleteById(organization.getId());
  }
}
