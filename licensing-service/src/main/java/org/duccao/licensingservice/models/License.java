package org.duccao.licensingservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "licenses")
public class License {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "license_id", nullable = false)
  private String licenseId;

  private String description;

  @Column(name = "organization_id", nullable = false)
  private String organizationId;

  @Column(name = "product_name", nullable = false)
  private String productName;

  @Column(name = "license_type", nullable = false)
  private String licenseType;

  @Column(name = "comment")
  private String comment;

  @Transient
  private String organizationName;

  @Transient
  private String contactName;

  @Transient
  private String contactPhone;

  @Transient
  private String contactEmail;

  public License withComment(String comment) {
    this.setComment(comment);
    return this;
  }

  public License() {
  }

  public License(String licenseId, String description, String organizationId, String productName, String licenseType,
                 String comment) {
    this.licenseId = licenseId;
    this.description = description;
    this.organizationId = organizationId;
    this.productName = productName;
    this.licenseType = licenseType;
    this.comment = comment;
  }

  public String getLicenseId() {
    return licenseId;
  }

  public void setLicenseId(String licenseId) {
    this.licenseId = licenseId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(String organizationId) {
    this.organizationId = organizationId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getLicenseType() {
    return licenseType;
  }

  public void setLicenseType(String licenseType) {
    this.licenseType = licenseType;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getOrganizationName() {
    return organizationName;
  }

  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }

  public String getContactName() {
    return contactName;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public String getContactPhone() {
    return contactPhone;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }

  public String getContactEmail() {
    return contactEmail;
  }

  public void setContactEmail(String contactEmail) {
    this.contactEmail = contactEmail;
  }
}
