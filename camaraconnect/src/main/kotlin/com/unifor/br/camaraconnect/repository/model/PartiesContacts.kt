//PartiesContacts.kt
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
@Table(name = "partiescontacts")
data class PartiesContacts(
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
    val datetimeUpdatedAt: LocalDateTime= LocalDateTime.now(),
    @ManyToOne
    @JoinColumn(name = "partyId",
        nullable=false,
        foreignKey= ForeignKey(name = "fkPartiesContacts")
    )
    val partyId: Parties
)