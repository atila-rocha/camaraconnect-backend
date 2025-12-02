package com.unifor.br.camaraconnect.repository

import com.unifor.br.camaraconnect.repository.model.Parties
import com.unifor.br.camaraconnect.repository.model.MediationCase
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional


interface PartiesRepository: JpaRepository<Parties, Int> {
    fun findByDocumentNumber(documentNumber: String): Optional<Parties>

    fun existsByDocumentNumber(documentNumber: String): Boolean

    fun findAllByNameContainingIgnoreCase(name: String): List<Parties>

    fun findAllByPartyType(partyType: String): List<Parties>

    fun findAllByCaseId(case: MediationCase): List<Parties>

    fun findAllByCaseId_CaseId(caseId: Int): List<Parties>

}