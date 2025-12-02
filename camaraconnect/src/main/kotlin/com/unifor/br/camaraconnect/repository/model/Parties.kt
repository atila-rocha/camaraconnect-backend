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
data class Parties private constructor(
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
){
    class Builder{
        private var name: String = ""
        private var documentNumber: String = ""
        private var partyType: String = ""
        private var caseId: MediationCase ?= null
        private var legalRepresentrativeId: MutableList<LegalRepresentative> = emptyList<LegalRepresentative>().toMutableList()
        private var contact: MutableList<PartiesContacts> = emptyList<PartiesContacts>().toMutableList()

        fun name(name: String) = apply { this.name=name }
        fun documentNumber(documentNumber: String) = apply { this.documentNumber=documentNumber }
        fun partyType(partyType: String) = apply { this.partyType=partyType }
        fun caseId(caseId: MediationCase) = apply { this.caseId=caseId }
        fun legalRepresentrativeId(legalRepresentrativeId: MutableList<LegalRepresentative>) = apply { this.legalRepresentrativeId=legalRepresentrativeId }
        fun contact(contact: MutableList<PartiesContacts>) = apply { this.contact=contact }
        fun build(): Parties{
            return Parties(
                name = name,
                documentNumber = documentNumber,
                partyType = partyType,
                caseId = caseId!!,
                legalRepresentrativeId = legalRepresentrativeId,
                contact = contact
            )
        }
    }
}