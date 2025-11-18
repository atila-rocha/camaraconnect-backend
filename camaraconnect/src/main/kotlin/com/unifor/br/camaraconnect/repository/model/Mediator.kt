package com.unifor.br.camaraconnect.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="mediator")
data class Mediator(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val mediatorId: Int?=null,//verificar
    val userId: Int?=null,//verificar
    //estudar como implementa FKs
    val registrationNumber: String,
)