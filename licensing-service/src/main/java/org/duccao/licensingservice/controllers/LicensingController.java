package org.duccao.licensingservice.controllers;

import org.duccao.licensingservice.models.License;
import org.duccao.licensingservice.services.LicenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/{organizationId}/licences")
public class LicensingController {

  private final LicenseService licenseService;

  public LicensingController(LicenseService licenseService) {
    this.licenseService = licenseService;
  }

  @GetMapping("/{id}/{clientType}")
  public ResponseEntity<License> getLicense(@PathVariable(name = "organizationId") String organizationId,
                                            @PathVariable(name = "id") String id,
                                            @PathVariable(name = "clientType") String clientType
  ) {
    License license = licenseService.getLicense(id, organizationId, clientType);
    return ResponseEntity.ok(license);
  }

  @PutMapping
  public ResponseEntity<License> updateLicense(@PathVariable("organizationId") String organizationId,
                                               @RequestBody License request) {
    return ResponseEntity.ok(licenseService.updateLicense(request, organizationId));
  }


  @PostMapping
  public ResponseEntity<License> createLicense(@PathVariable("organizationId") String organizationId,
                                               @RequestBody License request) {
    return ResponseEntity.ok(licenseService.createLicense(request, organizationId));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> deleteLicense(@PathVariable("organizationId") String organizationId,
                                              @PathVariable("id") String id) {
    return ResponseEntity.ok(licenseService.deleteLicense(id, organizationId));
  }
}
