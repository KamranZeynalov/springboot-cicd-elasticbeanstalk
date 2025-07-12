package com.company.cloudapp.entity;

import com.company.cloudapp.constant.ContractPaymentStatus;
import com.company.cloudapp.constant.ContractStatus;
import com.company.cloudapp.constant.ContractType;
import com.company.cloudapp.constant.ContractValidityPeriod;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ContractStatus status;
    @Enumerated(EnumType.STRING)
    private ContractType type;
    @Enumerated(EnumType.STRING)
    private ContractPaymentStatus paymentStatus;
    @Enumerated(EnumType.STRING)
    private ContractValidityPeriod validityPeriod;
    private String documentId;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    @ManyToOne
    @JoinColumn(name = "signed_by")
    private User signedBy;
    private LocalDateTime signedAt;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
