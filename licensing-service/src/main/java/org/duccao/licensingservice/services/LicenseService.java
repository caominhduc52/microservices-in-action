package org.duccao.licensingservice.services;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import org.duccao.licensingservice.configs.ServiceConfig;
import org.duccao.licensingservice.models.License;
import org.duccao.licensingservice.models.Organization;
import org.duccao.licensingservice.repositories.LicenseRepository;
import org.duccao.licensingservice.services.clients.OrganizationDiscoveryClient;
import org.duccao.licensingservice.services.clients.OrganizationFeignClient;
import org.duccao.licensingservice.services.clients.OrganizationRestClient;
import org.duccao.licensingservice.services.clients.OrganizationRestTemplateClient;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {

  private final LicenseRepository licenseRepository;
  private final ServiceConfig serviceConfig;
  private final OrganizationDiscoveryClient organizationDiscoveryClient;
  private final OrganizationRestTemplateClient organizationRestTemplateClient;
  private final OrganizationFeignClient organizationFeignClient;
  private final OrganizationRestClient organizationRestClient;

  public LicenseService(LicenseRepository licenseRepository, ServiceConfig serviceConfig,
                        OrganizationDiscoveryClient organizationDiscoveryClient,
                        OrganizationRestTemplateClient organizationRestTemplateClient,
                        OrganizationFeignClient organizationFeignClient, OrganizationRestClient organizationRestClient) {
    this.licenseRepository = licenseRepository;
    this.serviceConfig = serviceConfig;
    this.organizationDiscoveryClient = organizationDiscoveryClient;
    this.organizationRestTemplateClient = organizationRestTemplateClient;
    this.organizationFeignClient = organizationFeignClient;
    this.organizationRestClient = organizationRestClient;
  }

  public License getLicense(String id, String organizationId, String clientType) {
    License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, id);
    if (null == license) {
      throw new IllegalArgumentException(
        "Cannot find license with id: " + id + " in organization id: " + organizationId);
    }

    Organization organization = retrieveOrganizationInfo(organizationId, clientType);
    if (null != organization) {
      license.setOrganizationName(organization.getName());
      license.setContactName(organization.getContactName());
      license.setContactEmail(organization.getContactEmail());
      license.setContactPhone(organization.getContactPhone());
    }

    return license.withComment(serviceConfig.getProperty());
  }

  @CircuitBreaker(name = "organizationService")
  private Organization retrieveOrganizationInfo(String organizationId, String clientType) {
    return switch (clientType) {
      case "feign" -> {
        System.out.println("I am using the feign client");
        yield organizationFeignClient.getOrganization(organizationId);
      }
      case "rest" -> {
        System.out.println("I am using the rest client");
        yield organizationRestTemplateClient.getOrganization(organizationId);
      }
      case "discovery" -> {
        System.out.println("I am using the discovery client");
        yield organizationDiscoveryClient.getOrganization(organizationId);
      }
      case "restClient" -> {
        System.out.println("I am using the rest client");
        yield organizationRestClient.getOrganization(organizationId);
      }
      default -> organizationRestTemplateClient.getOrganization(organizationId);
    };
  }

  public License createLicense(License license, String organizationId) {
    license.setLicenseId(UUID.randomUUID().toString());
    licenseRepository.save(license);
    return license.withComment(serviceConfig.getProperty());
  }

  public License updateLicense(License license, String organizationId) {
    licenseRepository.save(license);
    return license.withComment(serviceConfig.getProperty());
  }

  public String deleteLicense(String licenseId, String organizationId) {
    License license = new License();
    license.setLicenseId(licenseId);
    licenseRepository.delete(license);
    return String.format("Deleting license with id %s for the organization %s", licenseId, organizationId);
  }

  @CircuitBreaker(name = "licenseService", fallbackMethod = "buildFallbackLicenses")
  @Bulkhead(name = "licenseServiceBulkhead", fallbackMethod = "buildFallbackLicenses")
  @Retry(name = "licenseServiceRetry", fallbackMethod = "buildFallbackLicenses")
  public List<License> getLicensesByOrganization(String organizationId) throws TimeoutException {
    return licenseRepository.findByOrganizationId(organizationId);
  }

  public List<License> buildFallbackLicenses(String organizationId, TimeoutException exception) {
    License license = new License();
    license.setLicenseId(UUID.randomUUID().toString());
    license.setComment("Sorry no license found for organization " + organizationId);
    return List.of(license);
  }

  private void sleep() throws TimeoutException {
    try {
      Thread.sleep(100);
      throw new java.util.concurrent.TimeoutException();
    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }
  }
}
