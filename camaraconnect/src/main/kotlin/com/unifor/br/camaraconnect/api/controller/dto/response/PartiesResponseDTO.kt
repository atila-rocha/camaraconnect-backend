package com.unifor.br.camaraconnect.api.controller.dto.response

import com.unifor.br.camaraconnect.api.controller.dto.request.LegalRepresentativeRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.request.PartiesContactsRequestDTO
import java.time.LocalDateTime

data class PartiesResponseDTO (
    val partyId: Int,
    val name: String,
    val documentNumber: String,
    val partyType: String,
    val caseId: Int,
    val legalRepresentativeId: List<Int> = emptyList(),
    val contact: List<Int> = emptyList(),
    val datetimeCreatedAt: LocalDateTime,
    val datetimeUpdatedAt: LocalDateTime,
)