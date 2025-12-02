//LegalRepresentativeContacts.kt
package com.unifor.br.camaraconnect.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.UpdateTimestamp

@Entity
@Table(name = "legalrepresentativecontacts")
data class LegalRepresentativeContacts private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val contactId: Int? = null,
    @Enumerated(EnumType.STRING)
    var contactType: ContactType,
    var contact: String,
    var isPrimary : Boolean = false,
    @CreationTimestamp
    val dtCreatedAt : LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    var datetimeUpdatedAt: LocalDateTime= LocalDateTime.now(),
    @ManyToOne
    @JoinColumn(name = "legalRepresentativeId",
        nullable=false,
        foreignKey= ForeignKey(name = "fkLegalContacts")
    )
    var legalRepresentativeId: LegalRepresentative
){
    class Builder{
        private var contactType: ContactType = ContactType.EMAIL
        private var contact: String = ""
        private var isPrimary: Boolean = false
        private var legalRepresentative: LegalRepresentative? = null

        fun contactType(contactType: ContactType) = apply { this.contactType = contactType}
        fun isPrimary(isPrimary: Boolean) = apply { this.isPrimary = isPrimary}
        fun legalRepresentative(legalRepresentative: LegalRepresentative) = apply { this.legalRepresentative = legalRepresentative}
        fun contact(contact: String) = apply { this.contact = contact}
        fun build(): LegalRepresentativeContacts{
            return LegalRepresentativeContacts(
                contactType=contactType,
                contact = contact,
                isPrimary = isPrimary,
                legalRepresentativeId = legalRepresentative!!
            )
        }
    }


}