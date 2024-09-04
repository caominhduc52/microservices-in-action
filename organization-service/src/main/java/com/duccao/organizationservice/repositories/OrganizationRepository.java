package com.duccao.organizationservice.repositories;

import com.duccao.organizationservice.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, String> {
}
