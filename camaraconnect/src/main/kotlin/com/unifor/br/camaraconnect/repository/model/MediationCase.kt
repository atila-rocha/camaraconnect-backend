//MediationCase
package com.unifor.br.camaraconnect.repository.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name="mediationcase")
data class MediationCase(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val caseId: Int? = null,
    val caseNum: Int,
    var description: String?="",
    @CreationTimestamp
    val datetimeCreatedAt: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    var datetimeUpdatedAt: LocalDateTime = LocalDateTime.now(),
    @Enumerated(value = EnumType.STRING)
    @Column(name = "caseStatus", nullable = false, length = 20)
    var caseStatus: CaseStatus= CaseStatus.EM_ANDAMENTO,
    @ManyToOne
    @JoinColumn(name = "mediatorId",
        nullable = false,
        foreignKey = ForeignKey(name = "fkCaseMediator")
    )
    var mediatorId: Mediator,//FK
    @OneToMany(mappedBy = "caseId",
        cascade = [jakarta.persistence.CascadeType.ALL],
        orphanRemoval = true)
    var partyId: MutableList<Parties> = mutableListOf()
)