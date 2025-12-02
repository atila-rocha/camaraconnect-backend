package com.unifor.br.camaraconnect.api.service

import com.unifor.br.camaraconnect.factory.PartiesContactsFactory
import com.unifor.br.camaraconnect.repository.MediatorRepository
import com.unifor.br.camaraconnect.repository.PartiesContactsRepository
import com.unifor.br.camaraconnect.repository.model.ContactType
import com.unifor.br.camaraconnect.repository.model.Mediator
import com.unifor.br.camaraconnect.repository.model.Parties
import com.unifor.br.camaraconnect.repository.model.PartiesContacts
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PartiesContactsService (
    private val partiesContactsRepository: PartiesContactsRepository,
    private val partiesService: PartiesService,
    private val partiesContactsFactory: PartiesContactsFactory

){
    fun createContact(
        contactType: ContactType,
        contact: String,
        isPrimary: Boolean = false,
        partyId: Int
    ): Optional<PartiesContacts>{
        val party =  partiesService.findPartyById(partyId).orElseThrow { RuntimeException("Parte nao encontrada") }
        val newContact = partiesContactsFactory.createContact(contactType, contact, isPrimary, party)
        val saved = partiesContactsRepository.save(newContact)
        return Optional.of(saved)
    }
    fun updateContact(id:Int, partyContact: PartiesContacts):Optional<PartiesContacts>{
        val contactOptional = partiesContactsRepository.findById(id)
        if (contactOptional.isEmpty){
            return Optional.empty()
        }
        val contactToUpdate= PartiesContacts(
            contactId = contactOptional.get().contactId,
            contactType = partyContact.contactType,
            contact = partyContact.contact,
            isPrimary = partyContact.isPrimary,
            partyId = contactOptional.get().partyId
        )
        val updatedContact= partiesContactsRepository.save(contactToUpdate)
        return Optional.of(updatedContact)
    }
    fun deleteContact(id: Int): Optional<PartiesContacts>{
        val contactOptional = partiesContactsRepository.findById(id)
        if (contactOptional.isEmpty){
            return Optional.empty()
        }
        partiesContactsRepository.deleteById(id)
        return Optional.of(contactOptional.get())
    }

    fun findAllByPartyId(party: Parties): List<PartiesContacts>{
        return partiesContactsRepository.findAllByPartyId(party)
    }

    fun findAllByPartyId_PartyId(partyId: Int): List<PartiesContacts>{
        return partiesContactsRepository.findAllByPartyId_PartyId(partyId)
    }

    fun findContactByPartyIdAndIsPrimaryTrue(party: Parties): List<PartiesContacts> {
        return partiesContactsRepository.findByPartyIdAndIsPrimaryTrue(party)
    }

    fun findByPartyId_PartyIdAndIsPrimaryTrue(partyId: Int): List<PartiesContacts> {
        return partiesContactsRepository.findByPartyId_PartyIdAndIsPrimaryTrue(partyId)
    }

    fun findAllByContactType(contactType: ContactType): List<PartiesContacts> {
        return partiesContactsRepository.findAllByContactType(contactType)
    }

    fun findAllByPartyIdAndContactType(party: Parties, contactType: ContactType): List<PartiesContacts> {
        return partiesContactsRepository.findAllByPartyIdAndContactType(party, contactType)
    }

    fun findAllByPartyId_PartyIdAndContactType(party: Int, contactType: ContactType): List<PartiesContacts> {
        return partiesContactsRepository.findAllByPartyId_PartyIdAndContactType(party, contactType)
    }

    fun findPartyByContact(contact: String): Optional<PartiesContacts>{
        val contactOptional = partiesContactsRepository.findByContact(contact)
        if (contactOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(contactOptional.get())
    }

    fun existsPartyByContact(contact: String): Boolean{
        return partiesContactsRepository.existsByContact(contact)

    }

    fun findContactById(id: Int): Optional<PartiesContacts>{
        val contactOptional = partiesContactsRepository.findById(id)
        if (contactOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(contactOptional.get())
    }
    fun findAllContacts(): List<PartiesContacts>{
        return partiesContactsRepository.findAll()
    }
}