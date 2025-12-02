package com.unifor.br.camaraconnect.factory

import com.unifor.br.camaraconnect.api.controller.dto.response.LegalRepresentativeContactsResponseDTO
import com.unifor.br.camaraconnect.repository.model.ContactType
import com.unifor.br.camaraconnect.repository.model.LegalRepresentative
import com.unifor.br.camaraconnect.repository.model.LegalRepresentativeContacts
import org.springframework.stereotype.Component


@Component
class LegalRepresentativeContactsFactory {
    fun createContact(
        contactType: ContactType,
        contact: String,
        isPrimary: Boolean = false,
        legalRepresentativeId: LegalRepresentative
    ): LegalRepresentativeContacts{
        val legalcontact = LegalRepresentativeContacts.Builder()
            .contactType(contactType)
            .contact(contact)
            .isPrimary(isPrimary)
            .legalRepresentative(legalRepresentativeId)
            .build()
        return legalcontact
    }

    fun createContactResponse(contact: LegalRepresentativeContacts): LegalRepresentativeContactsResponseDTO{
        return LegalRepresentativeContactsResponseDTO(
            contactId = contact.contactId!!,
            contactType = contact.contactType,
            contact = contact.contact,
            isPrimary = contact.isPrimary,
            datetimeCreatedAt = contact.dtCreatedAt,
            datetimeUpdatedAt = contact.datetimeUpdatedAt
        )
    }
}