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

@Entity
@Table(name="legal_representative")
data class LegalRepresentative(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val representativeId: Int,
    val name: String,
    val oabNumber: String,
    val oabState: String,
    @ManyToOne
    @JoinColumn(name = "partyId",
        nullable = false,
        foreignKey = ForeignKey(name = "fkLegalParty")
    )
    val partyId: Parties,
    @OneToMany(mappedBy = "legalRepresentativeId",
        cascade = [jakarta.persistence.CascadeType.ALL],
        orphanRemoval = true)
    val contact: List<LegalRepresentativeContacts> = emptyList(),
)