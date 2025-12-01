package com.unifor.br.camaraconnect.repository

import com.unifor.br.camaraconnect.repository.model.ContactType
import com.unifor.br.camaraconnect.repository.model.LegalRepresentative
import com.unifor.br.camaraconnect.repository.model.LegalRepresentativeContacts
import com.unifor.br.camaraconnect.repository.model.Parties
import com.unifor.br.camaraconnect.repository.model.PartiesContacts
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional


interface LegalRepresentativeContactsRepository: JpaRepository<LegalRepresentativeContacts, Int> {
    fun findAllByLegalRepresentativeId(representative: LegalRepresentative): List<LegalRepresentativeContacts>

    fun findAllByLegalRepresentativeId_LegalRepresentativeId(representativeId: Int): List<LegalRepresentativeContacts>

    fun findByLegalRepresentativeIdAndIsPrimaryTrue(representative: LegalRepresentative): List<LegalRepresentativeContacts>

    fun findByLegalRepresentativeId_LegalRepresentativeIdAndIsPrimaryTrue(representativeId: Int): List<LegalRepresentativeContacts>

    fun findAllByContactType(contactType: ContactType): List<LegalRepresentativeContacts>

    fun findAllByLegalRepresentativeIdAndContactType(representative: LegalRepresentative, contactType: ContactType): List<LegalRepresentativeContacts>

    fun findAllByLegalRepresentativeId_LegalRepresentativeIdAndContactType(representativeId: Int, contactType: ContactType): List<LegalRepresentativeContacts>

    fun findByContact(contact: String): Optional<LegalRepresentativeContacts>

    fun existsByContact(contact: String): Optional<Boolean>
}