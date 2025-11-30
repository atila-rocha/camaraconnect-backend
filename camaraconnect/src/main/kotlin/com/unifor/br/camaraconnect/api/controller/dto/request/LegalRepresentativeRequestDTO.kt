package com.unifor.br.camaraconnect.api.controller.dto.request

data class LegalRepresentativeRequestDTO (
    val name: String,
    val oabNumber: String,
    val oabState: String,
    val partyId: Int,
    val contacts: List<LegalRepresentativeRequestDTO> = emptyList()
)