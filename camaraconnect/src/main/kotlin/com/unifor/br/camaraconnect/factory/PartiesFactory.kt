package com.unifor.br.camaraconnect.factory

import com.unifor.br.camaraconnect.api.controller.dto.request.LegalRepresentativeRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.request.PartiesContactsRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.response.PartiesResponseDTO
import com.unifor.br.camaraconnect.repository.model.LegalRepresentative
import com.unifor.br.camaraconnect.repository.model.LegalRepresentativeContacts
import com.unifor.br.camaraconnect.repository.model.MediationCase
import com.unifor.br.camaraconnect.repository.model.Parties
import com.unifor.br.camaraconnect.repository.model.PartiesContacts
import org.springframework.stereotype.Component
import java.util.Collections

@Component
class PartiesFactory {
    fun createParty(name: String,
                    documentNumber: String,
                    partyType: String,
                    legalRepresentativeId: MutableList<LegalRepresentativeRequestDTO> = Collections.emptyList(),
                    contact: MutableList<PartiesContactsRequestDTO> = Collections.emptyList(), mediationCase: MediationCase): Parties{
        var party = Parties.Builder()
            .name(name)
            .documentNumber(documentNumber)
            .partyType(partyType)
            .caseId(mediationCase)
            .build()

        party.contact = contact.map {
            PartiesContacts.Builder()
                .contactType(it.contactType)
                .contact(it.contact)
                .isPrimary(it.isPrimary)
                .partyId(party)
                .build()
            }.toMutableList()

        party.legalRepresentrativeId= legalRepresentativeId.map {
            val representative = LegalRepresentative.Builder()
                .name(it.name)
                .oabNumber(it.oabNumber)
                .oabState(it.oabState)
                .partyId(party)
                .build()

            val representativeContacts = it.contacts.map {
                LegalRepresentativeContacts.Builder()
                    .contactType(it.contactType)
                    .contact(it.contact)
                    .isPrimary(it.isPrimary)
                    .legalRepresentative(representative)
                    .build()
            }.toMutableList()

            representative.contact=representativeContacts

            representative

        }.toMutableList()
        return party
    }

    fun createPartyResponse(party: Parties): PartiesResponseDTO{
        return PartiesResponseDTO(
            partyId = party.partyId!!,
            name = party.name,
            documentNumber = party.documentNumber,
            partyType = party.partyType,
            caseId = party.caseId.caseId!!,
            legalRepresentativeId = party.legalRepresentrativeId.map { it.representativeId!! },
            contact = party.contact.map { it.contactId!! },
            datetimeCreatedAt = party.datetimeCreatedAt,
            datetimeUpdatedAt = party.datetimeUpdatedAt,

            )
    }
}