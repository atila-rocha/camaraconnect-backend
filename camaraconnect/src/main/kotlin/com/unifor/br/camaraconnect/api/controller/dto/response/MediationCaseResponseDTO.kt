package com.unifor.br.camaraconnect.api.controller.dto.response

import com.unifor.br.camaraconnect.repository.model.CaseStatus
import java.time.LocalDateTime

data class MediationCaseResponseDTO (
    val caseId : Int,
    val caseNum : String,
    val description : String,
    val caseStatus : CaseStatus,
    val mediatorId : Int,
    val parties : List<Int>,
    val datetimeCreatedAt : LocalDateTime,
    val datetimeUpdatedAt : LocalDateTime,
    )