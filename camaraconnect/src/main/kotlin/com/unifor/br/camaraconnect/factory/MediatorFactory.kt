package com.unifor.br.camaraconnect.factory

import com.unifor.br.camaraconnect.api.controller.dto.request.MediatorRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.response.MediatorResponseDTO
import com.unifor.br.camaraconnect.repository.model.Mediator
import org.springframework.stereotype.Component

@Component
class MediatorFactory {
    fun createMediator(dto: MediatorRequestDTO): Mediator{
        return Mediator(
            registrationNumber = dto.resgistrationNumber,
            userId = dto.userId
        )
    }

    fun createMediatorResponse(mediator: Mediator): MediatorResponseDTO{
        return MediatorResponseDTO(
            mediatorId = mediator.mediatorId!!,
            registrationNumber = mediator.registrationNumber,
            userId = mediator.userId.userId!!,
            username = mediator.userId.username,
            datetimeCratedAt = mediator.datetimeCreatedAt,
            datetimeUpdatedAt = mediator.datetimeUpdatedAt,
            mediationCases = mediator.mediationCases
        )
    }
}