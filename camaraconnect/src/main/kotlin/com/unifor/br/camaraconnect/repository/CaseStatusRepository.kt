package com.unifor.br.camaraconnect.repository

import com.unifor.br.camaraconnect.repository.model.CaseStatus
import org.springframework.data.jpa.repository.JpaRepository


interface CaseStatusRepository: JpaRepository<CaseStatus, Int> {
}