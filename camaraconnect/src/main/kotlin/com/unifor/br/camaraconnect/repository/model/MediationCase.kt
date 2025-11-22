package com.unifor.br.camaraconnect.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name="mediationcase")
data class MediationCase(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val caseId: Int,
    val caseNum: Int,
    val description: String?="",
    @CreationTimestamp
    val datetimeCreatedAt: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    val datetimeUpdatedAt: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    @JoinColumn(name = "statusId",
        nullable = false,
        foreignKey = ForeignKey(name = "fkCaseStatus")
    )
    val statusId: CaseStatus,
    @ManyToOne
    @JoinColumn(name = "mediatorId",
        nullable = false,
        foreignKey = ForeignKey(name = "fkCaseMediator")
    )
    val mediatorId: Mediator,//FK

)