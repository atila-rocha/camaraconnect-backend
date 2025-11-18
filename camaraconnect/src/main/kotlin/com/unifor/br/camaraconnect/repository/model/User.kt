package com.unifor.br.camaraconnect.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.GeneratedValue
import java.time.LocalDate

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val userId: Int? = null,//é nulo?
    val username: String,
    val password: String,
    val email: String,
    val role: String?="MEDIATOR", //verificar
    val datetimeCreatedAt: LocalDate,
    val userType: String?="MEDIATOR"//verificar
)