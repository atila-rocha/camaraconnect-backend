package com.unifor.br.camaraconnect.api.controller.dto.response

import java.time.LocalDateTime


data class LegalRepresentativeResponseDTO (
    val representativeId: Int,
    val name: String,
    val oabNumber: String,
    val oabState: String,
    val partyId: Int,
    val contact: List<Int>,
    val datetimeCreatedAt: LocalDateTime,
    val datetimeUpdatedAt: LocalDateTime
)