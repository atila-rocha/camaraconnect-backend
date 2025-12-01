package com.unifor.br.camaraconnect.repository

import com.unifor.br.camaraconnect.repository.model.CaseStatus
import com.unifor.br.camaraconnect.repository.model.MediationCase
import com.unifor.br.camaraconnect.repository.model.Mediator
import com.unifor.br.camaraconnect.repository.model.Parties
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import javax.swing.text.html.Option


interface MediationCaseRepository: JpaRepository<MediationCase, Int> {
    //  USANDO JPA QUERY METHODS
    fun findByCaseNum(caseNum: String): Optional<MediationCase>

    fun findAllByCaseStatus(caseStatus: CaseStatus): List<MediationCase>
    //CREATED BY FIRST
    fun findAllByCaseStatusOrderByDatetimeCreatedAtDesc(caseStatus: CaseStatus): List<MediationCase>
    //UPDATED AT FIRST
    fun findByAllCaseStatusOrderByDatetimeUpdatedAtDesc(caseStatus: CaseStatus): List<MediationCase>

    fun findAllByMediatorId(mediator: Mediator): List<MediationCase>

    fun findAllByMediatorId_MediatorId(mediatorId: Int): List<MediationCase>

    fun findAllByMediatorIdAndCaseStatus(mediator: Mediator, caseStatus: CaseStatus): List<MediationCase>

    fun findAllByMediatorId_MediatorIdAndCaseStatus(mediatorId: Int, caseStatus: CaseStatus): List<MediationCase>

    fun findAllByPartyId_PartyId(partyId: Int): List<MediationCase>

    fun findAllByPartyId(partyId: Parties): List<MediationCase>

    fun findAllByPartyId_NameContainingIgnoreCase(name: String): List<MediationCase>

    fun countByCaseStatus(caseStatus: CaseStatus): Optional<Long>

    fun existsByCaseNum(caseNum: String): Optional<Boolean>

}