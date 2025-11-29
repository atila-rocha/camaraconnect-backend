//LegalRepresentative.kt
package com.unifor.br.camaraconnect.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.OneToMany
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name="legal_representative")
data class LegalRepresentative(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val representativeId: Int? = null,
    var name: String,
    var oabNumber: String,
    var oabState: String,
    @CreationTimestamp
    val datetimeCreatedAt: LocalDateTime= LocalDateTime.now(),
    @UpdateTimestamp
    var datetimeUpdatedAt: LocalDateTime= LocalDateTime.now(),
    @ManyToOne
    @JoinColumn(name = "partyId",
        nullable = false,
        foreignKey = ForeignKey(name = "fkLegalParty")
    )
    var partyId: Parties,
    @OneToMany(mappedBy = "legalRepresentativeId",
        cascade = [jakarta.persistence.CascadeType.ALL],
        orphanRemoval = true)
    var contact: MutableList<LegalRepresentativeContacts> = mutableListOf(),
)