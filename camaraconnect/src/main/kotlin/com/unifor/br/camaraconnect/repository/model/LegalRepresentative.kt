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
data class LegalRepresentative private constructor(
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
){
    class Builder{
        private var name : String = ""
        private var oabNumber : String = ""
        private var oabState : String = ""
        private var partyId : Parties? = null
        private var contact : MutableList<LegalRepresentativeContacts> = emptyList<LegalRepresentativeContacts>().toMutableList()

        fun name(name:String)= apply { this.name=name }
        fun oabNumber(oabNumber:String)= apply { this.oabNumber=oabNumber }
        fun oabState(oabState: String)= apply { this.oabState=oabState }
        fun partyId(partyId: Parties)= apply { this.partyId=partyId }
        fun contact(contact: MutableList<LegalRepresentativeContacts>)= apply { this.contact=contact }
        fun build(): LegalRepresentative{
            return LegalRepresentative(
                name = name,
                oabNumber = oabNumber,
                oabState = oabState,
                partyId = partyId!!,
                contact = contact
            )
        }
    }
}