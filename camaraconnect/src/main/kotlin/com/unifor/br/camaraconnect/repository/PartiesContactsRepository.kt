package com.unifor.br.camaraconnect.repository

import com.unifor.br.camaraconnect.repository.model.PartiesContacts
import org.springframework.data.jpa.repository.JpaRepository


interface PartiesContactsRepository: JpaRepository<PartiesContacts, Int> {
}