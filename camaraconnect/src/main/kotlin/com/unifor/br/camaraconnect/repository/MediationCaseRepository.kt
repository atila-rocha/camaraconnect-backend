package com.unifor.br.camaraconnect.repository

import com.unifor.br.camaraconnect.repository.model.CaseStatus
import com.unifor.br.camaraconnect.repository.model.MediationCase
import org.springframework.data.jpa.repository.JpaRepository


interface MediationCaseRepository: JpaRepository<MediationCase, Int> {
    //  USANDO JPA QUERY METHODS
    fun findByCaseNum(caseNum: Int): MediationCase?

    fun findByCaseStatus(caseStatus: CaseStatus): List<MediationCase>
    //CREATED BY FIRST
    fun findByCaseStatusOrderByDatetimeCreatedAtDesc(caseStatus: CaseStatus): List<MediationCase>
    //UPDATED AT FIRST
    fun findByCaseStatusOrderByDatetimeUpdatedAtDesc(caseStatus: CaseStatus): List<MediationCase>

    fun countByCaseStatus(caseStatus: CaseStatus): Long

    fun existsByCaseNum(caseNum: Int): Boolean

}