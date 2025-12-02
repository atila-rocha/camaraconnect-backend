package com.unifor.br.camaraconnect.factory

import com.unifor.br.camaraconnect.api.controller.dto.request.MediationCaseRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.response.MediationCaseResponseDTO
import com.unifor.br.camaraconnect.repository.model.MediationCase
import com.unifor.br.camaraconnect.repository.model.Mediator
import org.springframework.stereotype.Component


@Component
class MediationCaseFactory {
    fun  createMediationCase(caseNum: String, description: String? = "", mediator: Mediator): MediationCase{
        val mediation = MediationCase.Builder()
            .caseNum(caseNum)
            .description(description!!)
            .mediatorId(mediator)
            .build()
        return mediation
    }

    fun createMediationCaseResponse(mediationCase: MediationCase): MediationCaseResponseDTO{
        return MediationCaseResponseDTO(
            caseId = mediationCase.caseId!!,
            caseNum = mediationCase.caseNum,
            description = mediationCase.description!!,
            caseStatus = mediationCase.caseStatus,
            mediatorId = mediationCase.mediatorId.mediatorId!!,
            datetimeCreatedAt = mediationCase.datetimeCreatedAt,
            datetimeUpdatedAt = mediationCase.datetimeUpdatedAt
        )
    }
}