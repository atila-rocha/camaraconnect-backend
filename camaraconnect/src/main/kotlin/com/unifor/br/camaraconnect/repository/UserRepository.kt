package com.unifor.br.camaraconnect.repository

import com.unifor.br.camaraconnect.repository.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional


interface UserRepository : JpaRepository<User, Int> {
    fun findUserByEmail(email: String): Optional<User>

    fun findUserByUsername(username: String): Optional<User>
}