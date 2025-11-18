package com.unifor.br.camaraconnect.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="legal_representative")
data class LegalRepresentative(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val representativeId: Int?=null,//verificar
    val partyId: Int?=null,//verificar
    //estudar como implementa FKs
    val name: String,
    val oabNumber: String,
    val oabState: String,
    val contact: String,//implementar objeto Contact
)