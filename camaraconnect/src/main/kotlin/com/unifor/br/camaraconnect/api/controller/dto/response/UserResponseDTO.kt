package com.unifor.br.camaraconnect.api.controller.dto.response

import java.time.LocalDateTime

data class UserResponseDTO (
    val userId: Int,
    val username: String,
    val password: String,
    val email: String,
    val role: String?,
    val datetimeCreatedAt: LocalDateTime,
    val datetimeUpdatedAt: LocalDateTime,

)