package com.unifor.br.camaraconnect.factory

import com.unifor.br.camaraconnect.api.controller.dto.response.PartiesContactsResponseDTO
import com.unifor.br.camaraconnect.repository.model.ContactType
import com.unifor.br.camaraconnect.repository.model.Parties
import com.unifor.br.camaraconnect.repository.model.PartiesContacts
import org.springframework.stereotype.Component

@Component
class PartiesContactsFactory {
    fun createContact(
        contactType: ContactType,
        contact: String,
        isPrimary: Boolean = false,
        partyId: Parties
    ): PartiesContacts{
        return PartiesContacts(
            contactType= contactType,
            contact = contact,
            isPrimary = isPrimary,
            partyId = partyId
        )
    }

    fun createContactResponse(party: PartiesContacts): PartiesContactsResponseDTO{
        return PartiesContactsResponseDTO(
            contactId = party.contactId!!,
            contactType = party.contactType,
            contact = party.contact,
            isPrimary = party.isPrimary,
            datetimeCreatedAt = party.dtCreatedAt,
            datetimeUpdatedAt = party.datetimeUpdatedAt,
        )
    }

}