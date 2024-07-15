package org.duccao.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.time.Instant;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public abstract class AbstractEntity<I extends Serializable> {

  public abstract I getId();

  @CreatedDate
  @Column(name = "created_at")
  private Instant createdAt;

  @LastModifiedDate
  @Column(name = "modified_at")
  private Instant modifiedAt;

  @CreatedBy
  @Column(name = "created_by")
  private String createdBy;

  @LastModifiedBy
  @Column(name = "modified_by")
  private String modifiedBy;

  @Version
  @Column(name = "version", nullable = false, columnDefinition = "int default 0")
  private Integer version;

  public AbstractEntity() {
  }

  public AbstractEntity(Instant createdAt, Instant modifiedAt, String createdBy, String modifiedBy, Integer version) {
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
    this.createdBy = createdBy;
    this.modifiedBy = modifiedBy;
    this.version = version;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(Instant modifiedAt) {
    this.modifiedAt = modifiedAt;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return "AbstractEntity{" +
        "createdAt=" + createdAt +
        ", modifiedAt=" + modifiedAt +
        ", createdBy='" + createdBy + '\'' +
        ", modifiedBy='" + modifiedBy + '\'' +
        ", version=" + version +
        '}';
  }
}
