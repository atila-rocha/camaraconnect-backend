//Parties.kt
package com.unifor.br.camaraconnect.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name="parties")
data class Parties(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val partyId: Int? = null,
    var name: String,
    var documentNumber: String,
    var partyType: String,//pessoa fisica ou pj ENUM?
    @CreationTimestamp
    val datetimeCreatedAt: LocalDateTime= LocalDateTime.now(),
    @UpdateTimestamp
    var datetimeUpdatedAt: LocalDateTime= LocalDateTime.now(),
    @ManyToOne
    @JoinColumn(name = "caseId",
        nullable = false,
        foreignKey = ForeignKey(name = "fkPartiesCase")
    )
    val caseId: MediationCase,
    @OneToMany(mappedBy = "partyId",
        cascade = [jakarta.persistence.CascadeType.ALL],
        orphanRemoval = true
    )
    var legalRepresentrativeId: MutableList<LegalRepresentative> = mutableListOf(),
    @OneToMany(mappedBy = "partyId",
        cascade = [jakarta.persistence.CascadeType.ALL],
        orphanRemoval = true)
    var contact: MutableList<PartiesContacts> = mutableListOf(),
)