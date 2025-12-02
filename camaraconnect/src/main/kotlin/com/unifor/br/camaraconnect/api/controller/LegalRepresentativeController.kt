package com.unifor.br.camaraconnect.api.controller

import com.unifor.br.camaraconnect.api.controller.dto.request.LegalRepresentativeRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.response.LegalRepresentativeResponseDTO
import com.unifor.br.camaraconnect.api.service.LegalRepresentativeContactsService
import com.unifor.br.camaraconnect.api.service.LegalRepresentativeService
import com.unifor.br.camaraconnect.factory.LegalRepresetativeFactory
import com.unifor.br.camaraconnect.factory.PartiesContactsFactory
import com.unifor.br.camaraconnect.repository.PartiesRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("/representatives")
class LegalRepresentativeController (
    private val legalRepresentativeService: LegalRepresentativeService,
    private val legalRepresentativeContactsService: LegalRepresentativeContactsService,
    private val partyRepository: PartiesRepository,
    private val legalRepresetativeFactory: LegalRepresetativeFactory,
    private val partiesContactsFactory: PartiesContactsFactory,
    private val partiesRepository: PartiesRepository,
){
    @PostMapping
    fun createRepresentative(@RequestBody representativeRequestDTO: LegalRepresentativeRequestDTO): ResponseEntity<LegalRepresentativeResponseDTO>{
        val representativeOptional = legalRepresentativeService.createRepresentative(
            representativeRequestDTO.name,
            representativeRequestDTO.oabNumber,
            representativeRequestDTO.oabState,
            representativeRequestDTO.partyId,
            representativeRequestDTO.contacts
        )
        if (representativeOptional.isEmpty){
            return ResponseEntity.badRequest().build()
        }
        return ResponseEntity(legalRepresetativeFactory.createResponse(representativeOptional.get()), HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateRepresentative(@PathVariable id: Int, @RequestBody  representativeRequestDTO: LegalRepresentativeRequestDTO): ResponseEntity<LegalRepresentativeResponseDTO>{
        val representativeOptional = legalRepresentativeService.updateRepresentative(id,
            representativeRequestDTO.name,
            representativeRequestDTO.oabNumber,
            representativeRequestDTO.oabState,
            representativeRequestDTO.partyId,
            representativeRequestDTO.contacts,
        )
        if (representativeOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity(legalRepresetativeFactory.createResponse(representativeOptional.get()), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteRepresentative(@PathVariable id: Int): ResponseEntity<LegalRepresentativeResponseDTO>{
        val representativeOptional = legalRepresentativeService.deleteRepresentative(id)
        if (representativeOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity(legalRepresetativeFactory.createResponse(representativeOptional.get()), HttpStatus.NO_CONTENT)
    }

    @GetMapping("/{id}")
    fun getRepresentativeById(@PathVariable id: Int): ResponseEntity<LegalRepresentativeResponseDTO>{
        val representativeOptional = legalRepresentativeService.findRepresentativeById(id)
        if (representativeOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(legalRepresetativeFactory.createResponse(representativeOptional.get()))
    }

    @GetMapping("/oabNumber/{oabNumber}")
    fun getRepresentativeByOabNumber(@PathVariable oabNumber: String): ResponseEntity<LegalRepresentativeResponseDTO>{
        val representativeOptional = legalRepresentativeService.findRepresentativeByOabNumber(oabNumber)
        if (representativeOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(legalRepresetativeFactory.createResponse(representativeOptional.get()))
    }

    @GetMapping("/oabNumber/{oabNumber}/oabState/{oabState}")
    fun getRepresentativeByOabNumberAndOabState(@PathVariable oabNumber: String, @PathVariable oabState: String): ResponseEntity<LegalRepresentativeResponseDTO>{
        val representativeOptional = legalRepresentativeService.findRepresentativeByOabNumberAndOabState(oabNumber, oabState)
        if (representativeOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(legalRepresetativeFactory.createResponse(representativeOptional.get()))
    }

    @GetMapping("exists/{oabNumber}/{oabState}")
    fun existsRepresentativeByOabNumberAndOabState(@PathVariable oabNumber: String, @PathVariable oabState: String): ResponseEntity<Boolean>{
        val exists = legalRepresentativeService.existsRepresentativeByOabNumberAndOabState(oabNumber, oabState)
        return ResponseEntity.ok(exists)
    }

    @GetMapping("/oabState/{oabState}")
    fun getAllRepresentativesByOabState(@PathVariable oabState: String): ResponseEntity<List<LegalRepresentativeResponseDTO>>{
        val representatives = legalRepresentativeService.findAllByOabState(oabState)
        val representativeResponseDTOList = representatives.map { legalRepresetativeFactory.createResponse(it) }
        return ResponseEntity.ok(representativeResponseDTOList)
    }

    @GetMapping("/party/{partyId}")
    fun getAllRepresentativesByPartyId(@PathVariable partyId: Int): ResponseEntity<List<LegalRepresentativeResponseDTO>>{
        val representatives = legalRepresentativeService.findAllByPartyId_PartyId(partyId)
        val representativeResponseDTOList = representatives.map { legalRepresetativeFactory.createResponse(it) }
        return ResponseEntity.ok(representativeResponseDTOList)
    }

    @GetMapping("/name/{name}")
    fun getAllRepresentativesByNameContainingIgnoreCase(@PathVariable name: String): ResponseEntity<List<LegalRepresentativeResponseDTO>>{
        val representatives = legalRepresentativeService.findAllByNameContainingIgnoreCase(name)
        val representativeResponseDTOList = representatives.map { legalRepresetativeFactory.createResponse(it) }
        return ResponseEntity.ok(representativeResponseDTOList)
    }

    @GetMapping("/name/caseactive/{name}")
    fun getAllRepresentativesByName(@PathVariable name: String): ResponseEntity<List<LegalRepresentativeResponseDTO>>{
        val representatives = legalRepresentativeService.findAllByName(name)
        val representativeResponseDTOList = representatives.map { legalRepresetativeFactory.createResponse(it) }
        return ResponseEntity.ok(representativeResponseDTOList)
    }

    @GetMapping
    fun getAllRepresentatives(): ResponseEntity<List<LegalRepresentativeResponseDTO>>{
        val representatives = legalRepresentativeService.findAllRepresentatives()
        val representativeResponseDTOList = representatives.map { legalRepresetativeFactory.createResponse(it) }
        return ResponseEntity.ok(representativeResponseDTOList)
    }


}