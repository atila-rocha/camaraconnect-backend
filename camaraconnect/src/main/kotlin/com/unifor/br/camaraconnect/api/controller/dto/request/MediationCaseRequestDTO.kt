package com.unifor.br.camaraconnect.api.controller.dto.request

import com.unifor.br.camaraconnect.repository.model.CaseStatus
import com.unifor.br.camaraconnect.repository.model.Mediator
import com.unifor.br.camaraconnect.repository.model.Parties
import java.util.Collections.emptyList


data class MediationCaseRequestDTO(
    val caseNum: String,
    val description: String? = "",
    val mediatorId: Int,
    val caseStatus: CaseStatus? = CaseStatus.EM_ANDAMENTO
)
