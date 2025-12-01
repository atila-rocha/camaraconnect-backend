package com.unifor.br.camaraconnect.api.controller

import com.unifor.br.camaraconnect.api.controller.dto.request.UserRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.response.UserResponseDTO
import com.unifor.br.camaraconnect.api.service.UserService
import com.unifor.br.camaraconnect.factory.UserFactory
import com.unifor.br.camaraconnect.repository.model.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val userFactory: UserFactory,
) {
    @PostMapping
    fun createUser(@RequestBody userRequestDTO: UserRequestDTO): ResponseEntity<UserResponseDTO>{
        val userOptional = userService.createUser(userFactory.userResquestToUser(userRequestDTO))
        if (userOptional.isEmpty){
            return ResponseEntity.badRequest().build()
        }
        return ResponseEntity(userFactory.userToUserResponse(userOptional.get()), HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Int, @RequestBody userRequestDTO: UserRequestDTO): ResponseEntity<UserResponseDTO>{
        val userOptional = userService.updateUser(id, userFactory.userResquestToUser(userRequestDTO))
        if (userOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity(userFactory.userToUserResponse(userOptional.get()), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Int): ResponseEntity<UserResponseDTO>{
        val userOptional = userService.deleteUser(id)
        if (userOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity(userFactory.userToUserResponse(userOptional.get()), HttpStatus.NO_CONTENT)
    }

    @GetMapping("/{id}")
    fun getuserById(@PathVariable id: Int): ResponseEntity<UserResponseDTO>{
        val userOptional = userService.findUserById(id)
        if (userOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(userFactory.userToUserResponse(userOptional.get()))
    }

    @GetMapping("/{email}")
    fun getuserByEmail(@PathVariable email: String): ResponseEntity<UserResponseDTO>{
        val userOptional = userService.findUserByEmail(email)
        if (userOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(userFactory.userToUserResponse(userOptional.get()))
    }

    @GetMapping("/{username}")
    fun getuserByUsername(@PathVariable username: String): ResponseEntity<UserResponseDTO>{
        val userOptional = userService.findUserByUsername(username)
        if (userOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(userFactory.userToUserResponse(userOptional.get()))
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserResponseDTO>>{
        val users = userService.findAllUsers()
        val userResponseDTOList = users.map { userFactory.userToUserResponse(it) }
        return ResponseEntity.ok(userResponseDTOList)

    }

}