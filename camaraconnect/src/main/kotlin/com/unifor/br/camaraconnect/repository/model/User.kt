//User.kt
package com.unifor.br.camaraconnect.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.GeneratedValue
import jakarta.persistence.OneToOne
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val userId: Int,
    val username: String,
    val password: String,
    val email: String,
    val role: String?="MEDIATOR", //verificar
    val datetimeCreatedAt: LocalDateTime= LocalDateTime.now(),
    val datetimeUpdatedAt: LocalDateTime= LocalDateTime.now(),
    val userType: String?="MEDIATOR",//verificar
    @OneToOne(mappedBy = "userId",
        cascade = [jakarta.persistence.CascadeType.ALL],
        orphanRemoval = true)
    val mediators: Mediator?= null
)