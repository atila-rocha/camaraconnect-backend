package com.unifor.br.camaraconnect.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="parties")
data class Parties(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val partyId: Int?=null,//verificar
    val caseId: Int?=null,//verificar
    //estudar como implementa FKs
    val name: String,
    val documentNumber: String,
    val partyType: String,//pessoa fisica ou pj
    val contact: String,//implementar objeto Contact (FK)
)