//Mediator.kt
package com.unifor.br.camaraconnect.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.persistence.JoinColumn

@Entity
@Table(name="mediator")
data class Mediator(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val mediatorId: Int,
    val registrationNumber: String,
    @OneToOne
    @JoinColumn(name = "userId",
        nullable = false,
        foreignKey = ForeignKey(name = "fkMediatorUser")
    )
    val userId: User,
    @OneToMany(mappedBy = "mediatorId",
        cascade = [jakarta.persistence.CascadeType.ALL],
        orphanRemoval = true)
    val mediationCases: List<MediationCase> = emptyList()
)