package com.unifor.br.camaraconnect.api.controller.dto.response

data class UserResponseDTO (
    val userId: Int,
    val username: String,
    val password: String,
    val email: String,
    val role: String?,

)