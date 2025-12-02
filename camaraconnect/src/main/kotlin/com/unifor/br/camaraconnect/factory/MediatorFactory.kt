package com.unifor.br.camaraconnect.factory

import com.unifor.br.camaraconnect.api.controller.dto.request.MediatorRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.response.MediatorResponseDTO
import com.unifor.br.camaraconnect.repository.model.Mediator
import com.unifor.br.camaraconnect.repository.model.User
import org.springframework.stereotype.Component

@Component
class MediatorFactory {
    fun createMediator(
        resgistrationNumber: String,
        userId: Int, //se for DTO vai expor informacoes como username e senha
        mediationCases: List<Int> = emptyList(),
        user: User): Mediator{
        val mediator = Mediator.Builder()
            .registrationNumber(resgistrationNumber)
            .userId(user)
            .build()
        return mediator
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