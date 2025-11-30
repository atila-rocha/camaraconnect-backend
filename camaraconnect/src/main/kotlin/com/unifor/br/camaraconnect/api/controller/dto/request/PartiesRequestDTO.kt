package com.unifor.br.camaraconnect.api.controller.dto.request


data class PartiesRequestDTO(
    val name: String,
    val documentNumber: String,
    val partyType: String,
    val caseId: Int,
    val legalRepresentativeId: List<LegalRepresentativeRequestDTO> = emptyList(),
    val contact: List<PartiesContactsRequestDTO> = emptyList()
)
