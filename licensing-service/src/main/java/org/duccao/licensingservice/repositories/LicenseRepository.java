package org.duccao.licensingservice.repositories;

import java.util.List;
import org.duccao.licensingservice.models.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License, String> {

  List<License> findByOrganizationId(String organizationId);

  License findByOrganizationIdAndLicenseId(String organizationId, String licenseId);
}
