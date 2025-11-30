package com.unifor.br.camaraconnect.factory

import com.unifor.br.camaraconnect.api.controller.dto.request.UserRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.response.UserResponseDTO
import com.unifor.br.camaraconnect.repository.model.User
import org.springframework.stereotype.Component


@Component
class UserFactory {
    fun userResquestToUser(dto: UserRequestDTO): User{
        return User(
            username = dto.username,
            password = dto.password,
            email = dto.email,
            role = dto.role,
        )
    }

    fun userToUserResponse(user: User): UserResponseDTO{
        return UserResponseDTO(
            userId = user.userId!!,
            username = user.username,
            password = user.password,
            email = user.email,
            role = user.role,
            datetimeCreatedAt = user.datetimeCreatedAt,
            datetimeUpdatedAt = user.datetimeUpdatedAt

        )
    }
}