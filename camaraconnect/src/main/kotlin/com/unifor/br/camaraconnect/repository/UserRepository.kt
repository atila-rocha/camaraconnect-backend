package com.unifor.br.camaraconnect.repository

import com.unifor.br.camaraconnect.repository.model.User
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<User, Int> {
    fun findUserByEmail(email: String): User

    fun findUserByUsername(username: String): User
}