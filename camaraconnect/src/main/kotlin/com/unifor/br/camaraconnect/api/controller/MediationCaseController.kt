package com.unifor.br.camaraconnect.api.controller

import com.unifor.br.camaraconnect.api.controller.dto.request.MediationCaseRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.response.MediationCaseResponseDTO
import com.unifor.br.camaraconnect.api.service.MediationCaseService
import com.unifor.br.camaraconnect.api.service.MediatorService
import com.unifor.br.camaraconnect.factory.MediationCaseFactory
import com.unifor.br.camaraconnect.repository.MediatorRepository
import com.unifor.br.camaraconnect.repository.model.CaseStatus
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
@RequestMapping("/mediatonscases")
class MediationCaseController (
    private val mediationCaseService: MediationCaseService,
    private val mediationCaseFactory: MediationCaseFactory,
    private val mediatorService: MediatorService,
    private val mediatorRepository: MediatorRepository
){
    @PostMapping
    fun createMediaton(@RequestBody mediationCaseRequestDTO: MediationCaseRequestDTO): ResponseEntity<MediationCaseResponseDTO>{
        val mediatonOptional = mediationCaseService.createMediatonCase(mediationCaseRequestDTO.caseNum, mediationCaseRequestDTO.description, mediationCaseRequestDTO.mediatorId)
        if (mediatonOptional.isEmpty){
            return ResponseEntity.badRequest().build()
        }
        return ResponseEntity(mediationCaseFactory.createMediationCaseResponse(mediatonOptional.get()), HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateMediaton(@PathVariable id: Int, @RequestBody  mediationCaseRequestDTO: MediationCaseRequestDTO): ResponseEntity<MediationCaseResponseDTO>{
        val mediatonOptional = mediationCaseService.updateMediatonCase(id, mediationCaseRequestDTO.caseNum, mediationCaseRequestDTO.description, mediationCaseRequestDTO.mediatorId, mediationCaseRequestDTO.caseStatus!!)
        if (mediatonOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity(mediationCaseFactory.createMediationCaseResponse(mediatonOptional.get()), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteMediaton(@PathVariable id: Int): ResponseEntity<MediationCaseResponseDTO>{
        val mediatonOptional = mediationCaseService.deleteMediatonCase(id)
        if (mediatonOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity(mediationCaseFactory.createMediationCaseResponse(mediatonOptional.get()), HttpStatus.NO_CONTENT)
    }

    @GetMapping("caseNum/{caseNum}")
    fun getMediatonByCaseNum(@PathVariable caseNum: String): ResponseEntity<MediationCaseResponseDTO>{
        val mediatonOptional = mediationCaseService.findMediationCaseByCasenum(caseNum)
        if (mediatonOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(mediationCaseFactory.createMediationCaseResponse(mediatonOptional.get()))
    }

    @GetMapping("caseStatus/{caseStatus}")
    fun getMediationByCaseStatus(@PathVariable caseStatus: CaseStatus): ResponseEntity<List<MediationCaseResponseDTO>>{
        val mediatons = mediationCaseService.findMediationCaseByCaseStatus(caseStatus)
        val userResponseDTOList = mediatons.map { mediationCaseFactory.createMediationCaseResponse(it) }
        return ResponseEntity.ok(userResponseDTOList)
    }

    @GetMapping("caseStatusDt/{caseStatus}")
    fun getMediationByCaseStatusOrderByCreatedAt(@PathVariable caseStatus: CaseStatus): ResponseEntity<List<MediationCaseResponseDTO>>{
        val mediatons = mediationCaseService.findMediationCaseByCaseStatusOrderByCreatedAtDesc(caseStatus)
        val userResponseDTOList = mediatons.map { mediationCaseFactory.createMediationCaseResponse(it) }
        return ResponseEntity.ok(userResponseDTOList)
    }

    @GetMapping("caseStatusUp/{caseStatus}")
    fun getMediationByCaseStatusOrderByUpdatedAt(@PathVariable caseStatus: CaseStatus): ResponseEntity<List<MediationCaseResponseDTO>>{
        val mediatons = mediationCaseService.findMediationCaseByCaseStatusOrderByUpdatedAtDesc(caseStatus)
        val userResponseDTOList = mediatons.map { mediationCaseFactory.createMediationCaseResponse(it) }
        return ResponseEntity.ok(userResponseDTOList)
    }


    @GetMapping("mediator/{mediatorId}")
    fun getAllMediationByMediatiorId(@PathVariable mediatorId: Int): ResponseEntity<List<MediationCaseResponseDTO>>{
        val mediatons = mediationCaseService.findAllByMediatorIdInt(mediatorId)
        val userResponseDTOList = mediatons.map { mediationCaseFactory.createMediationCaseResponse(it) }
        return ResponseEntity.ok(userResponseDTOList)
    }

    @GetMapping("mediator/caseStatus/{mediatorId}/{caseStatus}")
    fun getAllMediationAndCaseStatus(@PathVariable mediatorId: Int, @PathVariable caseStatus: CaseStatus): ResponseEntity<List<MediationCaseResponseDTO>>{
        val mediatons = mediationCaseService.findAllByMediatorIdAndCaseStatusInt(mediatorId, caseStatus)
        val userResponseDTOList = mediatons.map { mediationCaseFactory.createMediationCaseResponse(it) }
        return ResponseEntity.ok(userResponseDTOList)
    }

    @GetMapping("party/{partyId}")
    fun getAllMediationByPartyId(@PathVariable partyId: Int): ResponseEntity<List<MediationCaseResponseDTO>>{
        val mediatons = mediationCaseService.findAllByPartyIdInt(partyId)
        val userResponseDTOList = mediatons.map { mediationCaseFactory.createMediationCaseResponse(it) }
        return ResponseEntity.ok(userResponseDTOList)
    }

    @GetMapping("partyName/{partyName}")
    fun getAllMediationByPartyName(@PathVariable partyName: String): ResponseEntity<List<MediationCaseResponseDTO>>{
        val mediatons = mediationCaseService.findAllByPartyName(partyName)
        val userResponseDTOList = mediatons.map { mediationCaseFactory.createMediationCaseResponse(it) }
        return ResponseEntity.ok(userResponseDTOList)
    }

    @GetMapping("/count/{status}")
    fun countCasesByStatus(@PathVariable status: CaseStatus): ResponseEntity<Long> {
        val count = mediationCaseService.countCasesbyCaseStatus(status)
        return ResponseEntity.ok(count)
    }

    @GetMapping("/exists/{caseNum}")
    fun existsByCaseNum(@PathVariable caseNum: String): ResponseEntity<Boolean> {
        val exists = mediationCaseService.existsByCaseNum(caseNum)
        return ResponseEntity.ok(exists)
    }

    @GetMapping("/{id}")
    fun getMediatonById(@PathVariable id: Int): ResponseEntity<MediationCaseResponseDTO>{
        val mediatonOptional = mediationCaseService.findMediatonCaseById(id)
        if (mediatonOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(mediationCaseFactory.createMediationCaseResponse(mediatonOptional.get()))
    }


    @GetMapping
    fun getAllMediatons(): ResponseEntity<List<MediationCaseResponseDTO>>{
        val mediatons = mediationCaseService.findAllMediationCases()
        val userResponseDTOList = mediatons.map { mediationCaseFactory.createMediationCaseResponse(it) }
        return ResponseEntity.ok(userResponseDTOList)
    }

}