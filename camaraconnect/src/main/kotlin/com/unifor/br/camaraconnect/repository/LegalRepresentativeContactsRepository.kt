package com.unifor.br.camaraconnect.repository

import com.unifor.br.camaraconnect.repository.model.ContactType
import com.unifor.br.camaraconnect.repository.model.LegalRepresentative
import com.unifor.br.camaraconnect.repository.model.LegalRepresentativeContacts
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional


interface LegalRepresentativeContactsRepository: JpaRepository<LegalRepresentativeContacts, Int> {
    fun findAllByLegalRepresentativeId(representative: LegalRepresentative): List<LegalRepresentativeContacts>

    fun findAllByLegalRepresentativeId_RepresentativeId(representativeId: Int): List<LegalRepresentativeContacts>

    fun findByLegalRepresentativeIdAndIsPrimaryTrue(representative: LegalRepresentative): Optional<LegalRepresentativeContacts>

    fun findByLegalRepresentativeId_RepresentativeIdAndIsPrimaryTrue(representativeId: Int): Optional<LegalRepresentativeContacts>

    fun findAllByContactType(contactType: ContactType): List<LegalRepresentativeContacts>

    fun findAllByLegalRepresentativeIdAndContactType(representative: LegalRepresentative, contactType: ContactType): List<LegalRepresentativeContacts>

    fun findAllByLegalRepresentativeId_RepresentativeIdAndContactType(representativeId: Int, contactType: ContactType): List<LegalRepresentativeContacts>

    fun findByContact(contact: String): Optional<LegalRepresentativeContacts>

    fun existsByContact(contact: String): Boolean
}