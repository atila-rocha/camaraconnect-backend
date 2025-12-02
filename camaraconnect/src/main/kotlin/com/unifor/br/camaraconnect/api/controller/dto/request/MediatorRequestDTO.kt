package com.unifor.br.camaraconnect.api.controller.dto.request

import com.unifor.br.camaraconnect.repository.model.User

data class MediatorRequestDTO (
    val resgistrationNumber: String,
    val userId: Int, //se for DTO vai expor informacoes como username e senha
    val mediationCases: List<Int> = emptyList()
)