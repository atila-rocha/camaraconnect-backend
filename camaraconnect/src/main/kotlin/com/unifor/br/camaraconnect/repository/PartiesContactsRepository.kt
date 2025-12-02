package com.unifor.br.camaraconnect.repository

import com.unifor.br.camaraconnect.repository.model.PartiesContacts
import com.unifor.br.camaraconnect.repository.model.Parties
import com.unifor.br.camaraconnect.repository.model.ContactType
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional


interface PartiesContactsRepository: JpaRepository<PartiesContacts, Int> {

    fun findAllByPartyId(party: Parties): List<PartiesContacts>

    fun findAllByPartyId_PartyId(partyId: Int): List<PartiesContacts>

    fun findByPartyIdAndIsPrimaryTrue(party: Parties): List<PartiesContacts>

    fun findByPartyId_PartyIdAndIsPrimaryTrue(partyId: Int): List<PartiesContacts>

    fun findAllByContactType(contactType: ContactType): List<PartiesContacts>

    fun findAllByPartyIdAndContactType(party: Parties, contactType: ContactType): List<PartiesContacts>

    fun findAllByPartyId_PartyIdAndContactType(party: Int, contactType: ContactType): List<PartiesContacts>

    fun findByContact(contact: String): Optional<PartiesContacts>

    fun existsByContact(contact: String): Boolean
}