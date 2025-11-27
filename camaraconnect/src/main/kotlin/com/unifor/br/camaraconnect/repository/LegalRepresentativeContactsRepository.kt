package com.unifor.br.camaraconnect.repository

import com.unifor.br.camaraconnect.repository.model.LegalRepresentativeContacts
import org.springframework.data.jpa.repository.JpaRepository


interface LegalRepresentativeContactsRepository: JpaRepository<LegalRepresentativeContacts, Int> {
}