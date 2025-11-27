package com.unifor.br.camaraconnect.repository

import com.unifor.br.camaraconnect.repository.model.LegalRepresentative
import org.springframework.data.jpa.repository.JpaRepository


interface LegalRepresentativeRepository: JpaRepository<LegalRepresentative, Int> {
}