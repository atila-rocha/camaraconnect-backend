package com.unifor.br.camaraconnect.repository

import com.unifor.br.camaraconnect.repository.model.MediationCase
import org.springframework.data.jpa.repository.JpaRepository


interface MediationCaseRepository: JpaRepository<MediationCase, Int> {
}