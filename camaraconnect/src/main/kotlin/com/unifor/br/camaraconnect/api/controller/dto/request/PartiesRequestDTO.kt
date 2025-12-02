package com.unifor.br.camaraconnect.api.controller.dto.request

import java.util.Collections.emptyList

data class PartiesRequestDTO(
    val name: String,
    val documentNumber: String,
    val partyType: String,
    val caseId: Int,
    val legalRepresentativeId: MutableList<LegalRepresentativeRequestDTO> = emptyList(),
    val contact: MutableList<PartiesContactsRequestDTO> = emptyList()
)
