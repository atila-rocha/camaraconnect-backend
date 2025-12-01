package com.unifor.br.camaraconnect.api.service

import com.unifor.br.camaraconnect.repository.UserRepository
import com.unifor.br.camaraconnect.repository.model.User
import org.springframework.stereotype.Service
import java.util.Optional


@Service
class UserService (
    private val  userRepository: UserRepository
){
    fun createUser(user: User): Optional<User>{
        val newUser = userRepository.save(user)
        return Optional.of(newUser)
    }
    fun updateUser(id:Int, user: User): Optional<User>{
        val userOptional = userRepository.findById(id)
        if (userOptional.isEmpty){
            return Optional.empty()
        }
        val userToUpdate= User(
            userId = userOptional.get().userId,
            username=user.username,
            password=user.password,
            email=user.email
        )
        val updatedUser= userRepository.save(userToUpdate)
        return Optional.of(updatedUser)
    }

    fun deleteUser(id:Int): Optional<User>{
        val userOptional = userRepository.findById(id)
        if (userOptional.isEmpty){
            return Optional.empty()
        }
        userRepository.deleteById(id)
        return Optional.of(userOptional.get())
    }

    fun findUserByEmail(email: String): Optional<User>{
        val userOptional = userRepository.findUserByEmail(email)
        if (userOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(userOptional.get())
    }

    fun findUserByUsername(username: String): Optional<User>{
        val userOptional = userRepository.findUserByUsername(username)
        if (userOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(userOptional.get())
    }

    fun findUserById(id: Int): Optional<User>{
        val userOptional = userRepository.findById(id)
        if (userOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(userOptional.get())
    }

    fun findAllUsers(): List<User>{
        return userRepository.findAll()
    }
}