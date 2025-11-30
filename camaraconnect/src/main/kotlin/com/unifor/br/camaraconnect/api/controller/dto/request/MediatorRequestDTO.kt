package com.unifor.br.camaraconnect.api.controller.dto.request

data class MediatorRequestDTO (
    val resgistratioNumber: String,
    val userId: Int, //se for DTO vai expor informacoes como username e senha
    val mediationCases: List<Int> = emptyList()
)