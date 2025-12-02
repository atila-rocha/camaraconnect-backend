package com.unifor.br.camaraconnect.factory

import com.unifor.br.camaraconnect.api.controller.dto.request.LegalRepresentativeContactsRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.response.LegalRepresentativeResponseDTO
import com.unifor.br.camaraconnect.repository.model.LegalRepresentative
import com.unifor.br.camaraconnect.repository.model.LegalRepresentativeContacts
import com.unifor.br.camaraconnect.repository.model.Parties
import org.springframework.stereotype.Component

@Component
class LegalRepresentativeFactory {
    fun createRepresentative(
        name: String,
        oabNumber: String,
        oabState: String,
        partyId: Int,
        contacts: List<LegalRepresentativeContactsRequestDTO> = emptyList(),
        party: Parties
    ): LegalRepresentative{
        val representative = LegalRepresentative.Builder()
            .name(name)
            .oabNumber(oabNumber)
            .oabState(oabState)
            .partyId(party)
            .build()
        val contactsEntities = contacts.map {
            val representativeContact= LegalRepresentativeContacts.Builder()
                .contactType(it.contactType)
                .contact(it.contact)
                .isPrimary(it.isPrimary)
                .legalRepresentative(representative)
                .build()
            representativeContact
        }.toMutableList()

        representative.contact=contactsEntities

        return representative
    }

    fun createResponse(representative: LegalRepresentative): LegalRepresentativeResponseDTO{
        return LegalRepresentativeResponseDTO(
            representativeId = representative.representativeId!!,
            name = representative.name,
            oabNumber = representative.oabNumber,
            oabState = representative.oabState,
            partyId = representative.partyId.partyId!!,
            contact = representative.contact.map { it.contactId!! },
            datetimeCreatedAt = representative.datetimeCreatedAt,
            datetimeUpdatedAt = representative.datetimeUpdatedAt
        )
    }
}