package com.unifor.br.camaraconnect.api.service


import com.unifor.br.camaraconnect.factory.LegalRepresentativeContactsFactory
import com.unifor.br.camaraconnect.repository.model.ContactType
import com.unifor.br.camaraconnect.repository.model.LegalRepresentativeContacts
import com.unifor.br.camaraconnect.repository.LegalRepresentativeContactsRepository
import com.unifor.br.camaraconnect.repository.LegalRepresentativeRepository
import com.unifor.br.camaraconnect.repository.model.LegalRepresentative
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class LegalRepresentativeContactsService (
    private val  legalRepresentativeContactsRepository: LegalRepresentativeContactsRepository,
    private val  legalRepresentativeRepository: LegalRepresentativeRepository,
    private val  legalRepresentativeContactsFactory: LegalRepresentativeContactsFactory

){
    fun createContact(
        contactType: ContactType,
        contact: String,
        isPrimary: Boolean = false,
        legalRepresentativeId: Int
    ): Optional<LegalRepresentativeContacts>{
        val representative =legalRepresentativeRepository.findById(legalRepresentativeId).orElseThrow { RuntimeException("Representante não encontrado") }
        val newContact = legalRepresentativeContactsFactory.createContact(contactType,contact,isPrimary,representative)
        val saved = legalRepresentativeContactsRepository.save(newContact)
        return Optional.of(saved)
    }
    fun updateContact(
        id:Int,
        contactType: ContactType,
        contact: String,
        isPrimary: Boolean = false,
        legalRepresentativeId: Int):Optional<LegalRepresentativeContacts>{
        val contactOptional = legalRepresentativeContactsRepository.findById(id)
        val representative =legalRepresentativeRepository.findById(legalRepresentativeId).orElseThrow { RuntimeException("Representante não encontrado") }
        if (contactOptional.isEmpty){
            return Optional.empty()
        }
        val contactToUpdate= legalRepresentativeContactsFactory.createContact(
            contactType,
            contact,
            isPrimary,
            representative
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

    fun findAllByLegalRepresentativeId_RepresentativeId(representativeId: Int):List<LegalRepresentativeContacts>{
        return legalRepresentativeContactsRepository.findAllByLegalRepresentativeId_RepresentativeId(representativeId)
    }

    fun findByLegalRepresentativeIdAndIsPrimaryTrue(representative: LegalRepresentative):Optional<LegalRepresentativeContacts> {
        return legalRepresentativeContactsRepository.findByLegalRepresentativeIdAndIsPrimaryTrue(representative)
    }

    fun findByLegalRepresentativeId_RepresentativeIdAndIsPrimaryTrue(representativeId: Int): Optional<LegalRepresentativeContacts> {
        return legalRepresentativeContactsRepository.findByLegalRepresentativeId_RepresentativeIdAndIsPrimaryTrue(representativeId)
    }

    fun findAllByContactType(contactType: ContactType): List<LegalRepresentativeContacts> {
        return legalRepresentativeContactsRepository.findAllByContactType(contactType)
    }

    fun findAllByLegalRepresentativeIdAndContactType(representative: LegalRepresentative, contactType: ContactType): List<LegalRepresentativeContacts> {
        return legalRepresentativeContactsRepository.findAllByLegalRepresentativeIdAndContactType(representative, contactType)
    }

    fun findAllByLegalRepresentativeId_RepresentativeIdAndContactType(representativeId: Int, contactType: ContactType): List<LegalRepresentativeContacts> {
        return legalRepresentativeContactsRepository.findAllByLegalRepresentativeId_RepresentativeIdAndContactType(representativeId, contactType)
    }

    fun findContactByContact(contact: String): Optional<LegalRepresentativeContacts>{
        val contactOptional = legalRepresentativeContactsRepository.findByContact(contact)
        if (contactOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(contactOptional.get())
    }

    fun existsContactByContact(contact: String): Boolean{
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