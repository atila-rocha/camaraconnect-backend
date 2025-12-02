package com.unifor.br.camaraconnect.api.controller

import com.unifor.br.camaraconnect.api.controller.dto.request.MediatorRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.request.UserRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.response.MediatorResponseDTO
import com.unifor.br.camaraconnect.api.service.MediatorService
import com.unifor.br.camaraconnect.factory.MediatorFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mediators")
class MediatorController (
    private val mediatorService: MediatorService,
    private val mediatorFactory: MediatorFactory,
){
    @PostMapping
    fun createMediator(@RequestBody mediatorRequestDTO: MediatorRequestDTO): ResponseEntity<MediatorResponseDTO>{
        val mediatorOptional = mediatorService.createMediator(mediatorFactory.createMediator(mediatorRequestDTO))
        if (mediatorOptional.isEmpty){
            return ResponseEntity.badRequest().build()
        }
        return ResponseEntity(mediatorFactory.createMediatorResponse(mediatorOptional.get()), HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateMediator(@PathVariable id: Int, @RequestBody  mediatorRequestDTO: MediatorRequestDTO): ResponseEntity<MediatorResponseDTO>{
        val mediatorOptional = mediatorService.updateMediator(id, mediatorFactory.createMediator(mediatorRequestDTO))
        if (mediatorOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity(mediatorFactory.createMediatorResponse(mediatorOptional.get()), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteMediator(@PathVariable id: Int): ResponseEntity<MediatorResponseDTO>{
        val mediatorOptional = mediatorService.deleteMediator(id)
        if (mediatorOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity(mediatorFactory.createMediatorResponse(mediatorOptional.get()), HttpStatus.NO_CONTENT)
    }

    @GetMapping("/{id}")
    fun getMediatorById(@PathVariable id: Int): ResponseEntity<MediatorResponseDTO>{
        val mediatorOptional = mediatorService.findMediatorById(id)
        if (mediatorOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(mediatorFactory.createMediatorResponse(mediatorOptional.get()))
    }

    @GetMapping("/{registraionNumber}")
    fun getMediatorByRegistrationNumber(@PathVariable registrationNumber: String): ResponseEntity<MediatorResponseDTO>{
        val mediatorOptional = mediatorService.findMediatorByRegistrationNumber(registrationNumber)
        if (mediatorOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(mediatorFactory.createMediatorResponse(mediatorOptional.get()))
    }


    @GetMapping
    fun getAllMediators(): ResponseEntity<List<MediatorResponseDTO>>{
        val mediators = mediatorService.findAllMediators()
        val userResponseDTOList = mediators.map { mediatorFactory.createMediatorResponse(it) }
        return ResponseEntity.ok(userResponseDTOList)

    }
}