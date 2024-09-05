package com.duccao.organizationservice.controllers;

import com.duccao.organizationservice.models.Organization;
import com.duccao.organizationservice.services.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description of the class goes here.
 *
 * @author Duc Cao
 * @version 1.0
 * @since 7/13/2024
 */

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {

  private final OrganizationService organizationService;

  public OrganizationController(OrganizationService organizationService) {
    this.organizationService = organizationService;
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Organization> getOrganization(@PathVariable("id") String id) {
    return ResponseEntity.ok(organizationService.findById(id));
  }

  @PutMapping(value = "/{id}")
  public void updateOrganization(@PathVariable("id") String id, @RequestBody Organization organization) {
    organizationService.update(organization);
  }

  @PostMapping
  public ResponseEntity<Organization> saveOrganization(@RequestBody Organization organization) {
    return ResponseEntity.ok(organizationService.create(organization));
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrganization(@PathVariable("id") String id, @RequestBody Organization organization) {
    organizationService.delete(organization);
  }
}
