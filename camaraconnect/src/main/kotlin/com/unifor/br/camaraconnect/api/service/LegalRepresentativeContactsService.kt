package com.unifor.br.camaraconnect.api.service


import com.unifor.br.camaraconnect.repository.model.ContactType
import com.unifor.br.camaraconnect.repository.model.LegalRepresentativeContacts
import com.unifor.br.camaraconnect.repository.LegalRepresentativeContactsRepository
import com.unifor.br.camaraconnect.repository.model.LegalRepresentative
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class LegalRepresentativeContactsService (
    private val  legalRepresentativeContactsRepository: LegalRepresentativeContactsRepository

){
    fun createContact(legalContact: LegalRepresentativeContacts): Optional<LegalRepresentativeContacts>{
        val newContact = legalRepresentativeContactsRepository.save(legalContact)
        return Optional.of(newContact)
    }
    fun updateContact(id:Int, legalContact: LegalRepresentativeContacts):Optional<LegalRepresentativeContacts>{
        val contactOptional = legalRepresentativeContactsRepository.findById(id)
        if (contactOptional.isEmpty){
            return Optional.empty()
        }
        val contactToUpdate= LegalRepresentativeContacts(
            contactId = contactOptional.get().contactId,
            contactType = legalContact.contactType,
            contact = legalContact.contact,
            isPrimary = legalContact.isPrimary,
            legalRepresentativeId = contactOptional.get().legalRepresentativeId
        )
        val updatedContact= legalRepresentativeContactsRepository.save(contactToUpdate)
        return Optional.of(updatedContact)
    }
    fun deleteContact(id: Int): Optional<LegalRepresentativeContacts>{
        val contactOptional = legalRepresentativeContactsRepository.findById(id)
        if (contactOptional.isEmpty){
            return Optional.empty()
        }
        legalRepresentativeContactsRepository.deleteById(id)
        return Optional.of(contactOptional.get())
    }

    fun findAllByLegalRepresentativeId(representative: LegalRepresentative):List<LegalRepresentativeContacts>{
        return legalRepresentativeContactsRepository.findAllByLegalRepresentativeId(representative)
    }

    fun findAllByLegalRepresentativeId_LegalRepresentativeId(representativeId: Int):List<LegalRepresentativeContacts>{
        return legalRepresentativeContactsRepository.findAllByLegalRepresentativeId_LegalRepresentativeId(representativeId)
    }

    fun findByLegalRepresentativeIdAndIsPrimaryTrue(representative: LegalRepresentative):List<LegalRepresentativeContacts> {
        return legalRepresentativeContactsRepository.findByLegalRepresentativeIdAndIsPrimaryTrue(representative)
    }

    fun findByLegalRepresentativeId_LegalRepresentativeIdAndIsPrimaryTrue(representativeId: Int): List<LegalRepresentativeContacts> {
        return legalRepresentativeContactsRepository.findByLegalRepresentativeId_LegalRepresentativeIdAndIsPrimaryTrue(representativeId)
    }

    fun findAllByContactType(contactType: ContactType): List<LegalRepresentativeContacts> {
        return legalRepresentativeContactsRepository.findAllByContactType(contactType)
    }

    fun findAllByLegalRepresentativeIdAndContactType(representative: LegalRepresentative, contactType: ContactType): List<LegalRepresentativeContacts> {
        return legalRepresentativeContactsRepository.findAllByLegalRepresentativeIdAndContactType(representative, contactType)
    }

    fun findAllByLegalRepresentativeId_LegalRepresentativeIdAndContactType(representativeId: Int, contactType: ContactType): List<LegalRepresentativeContacts> {
        return legalRepresentativeContactsRepository.findAllByLegalRepresentativeId_LegalRepresentativeIdAndContactType(representativeId, contactType)
    }

    fun findRepresentativeByContact(contact: String): Optional<LegalRepresentativeContacts>{
        val contactOptional = legalRepresentativeContactsRepository.findByContact(contact)
        if (contactOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(contactOptional.get())
    }

    fun existsRepresentativeByContact(contact: String): Optional<Boolean>{
        return legalRepresentativeContactsRepository.existsByContact(contact)

    }

    fun findContactById(id: Int): Optional<LegalRepresentativeContacts>{
        val contactOptional = legalRepresentativeContactsRepository.findById(id)
        if (contactOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(contactOptional.get())
    }
    fun findAllContacts(): List<LegalRepresentativeContacts>{
        return legalRepresentativeContactsRepository.findAll()
    }
}