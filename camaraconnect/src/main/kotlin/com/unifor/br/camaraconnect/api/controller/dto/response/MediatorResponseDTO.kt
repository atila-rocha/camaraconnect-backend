package com.unifor.br.camaraconnect.api.controller.dto.response

import com.unifor.br.camaraconnect.repository.model.MediationCase
import java.time.LocalDateTime
import java.util.Collections.emptyList

data class MediatorResponseDTO (
    val mediatorId: Int,
    val registrationNumber:String,
    val userId: Int, //pode ser UserResponse?
    val username: String,
    val datetimeCratedAt: LocalDateTime,
    val datetimeUpdatedAt: LocalDateTime,
    val mediationCases: MutableList<MediationCase> = emptyList()

)