package com.unifor.br.camaraconnect.api.controller.dto.request

data class UserRequestDTO (
    val username: String,
    val password: String,
    val email: String,
    val role: String? = "MEDIATOR",

)