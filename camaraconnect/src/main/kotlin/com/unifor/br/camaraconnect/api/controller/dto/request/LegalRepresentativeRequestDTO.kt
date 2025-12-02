package com.unifor.br.camaraconnect.api.controller.dto.request

import com.unifor.br.camaraconnect.repository.model.LegalRepresentativeContacts

data class LegalRepresentativeRequestDTO (
    val name: String,
    val oabNumber: String,
    val oabState: String,
    val partyId: Int,
    val contacts: List<LegalRepresentativeContactsRequestDTO> = emptyList()
)