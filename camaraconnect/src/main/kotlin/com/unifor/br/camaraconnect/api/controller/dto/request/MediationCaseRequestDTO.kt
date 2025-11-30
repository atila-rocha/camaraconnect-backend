package com.unifor.br.camaraconnect.api.controller.dto.request



data class MediationCaseRequestDTO(
    val caseNum: String,
    val description: String? = "",
    val mediatorId: Int,
    val partyId: List<Int> = emptyList()
)
