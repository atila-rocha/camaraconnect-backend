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
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name="mediator")
data class Mediator private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val mediatorId: Int? = null,
    val registrationNumber: String,
    @CreationTimestamp
    val datetimeCreatedAt: LocalDateTime= LocalDateTime.now(),
    @UpdateTimestamp
    var datetimeUpdatedAt: LocalDateTime= LocalDateTime.now(),
    @OneToOne
    @JoinColumn(name = "userId",
        nullable = false,
        foreignKey = ForeignKey(name = "fkMediatorUser")
    )
    val userId: User,
    @OneToMany(mappedBy = "mediatorId",
        cascade = [jakarta.persistence.CascadeType.ALL],
        orphanRemoval = true)
    var mediationCases: MutableList<MediationCase> = mutableListOf()
){
    class Builder{
        private var mediatorId: Int ?=null
        private var registrationNumber: String=""
        private var userId: User?= null
        private var mediationCases: MutableList<MediationCase> = emptyList<MediationCase>().toMutableList()

        fun mediatorId(mediatorId: Int) = apply { this.mediatorId=mediatorId }
        fun registrationNumber(registrationNumber: String) = apply { this.registrationNumber=registrationNumber }
        fun userId(userId: User) = apply { this.userId=userId }
        fun mediationCases(mediationCases: MutableList<MediationCase>) = apply { this.mediationCases=mediationCases }

        fun build(): Mediator{
            return Mediator(
                registrationNumber = registrationNumber,
                userId = userId!!,
                mediationCases = mediationCases
            )
        }
    }


}