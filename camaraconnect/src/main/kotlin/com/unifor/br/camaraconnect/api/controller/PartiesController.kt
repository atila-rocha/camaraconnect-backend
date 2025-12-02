package com.unifor.br.camaraconnect.api.controller

import com.unifor.br.camaraconnect.api.controller.dto.request.PartiesContactsRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.request.PartiesRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.response.PartiesContactsResponseDTO
import com.unifor.br.camaraconnect.api.controller.dto.response.PartiesResponseDTO
import com.unifor.br.camaraconnect.api.service.MediationCaseService
import com.unifor.br.camaraconnect.api.service.PartiesContactsService
import com.unifor.br.camaraconnect.api.service.PartiesService
import com.unifor.br.camaraconnect.factory.PartiesContactsFactory
import com.unifor.br.camaraconnect.factory.PartiesFactory
import com.unifor.br.camaraconnect.repository.model.ContactType
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
@RequestMapping("/parties")
class PartiesController (
    private val partiesService: PartiesService,
    private val partiesContactsService: PartiesContactsService,
    private val mediationCaseService: MediationCaseService,
    private val partiesFactory: PartiesFactory,
    private val partiesContactsFactory: PartiesContactsFactory
    ){
    @PostMapping
    fun createParty(@RequestBody partiesRequestDTO: PartiesRequestDTO): ResponseEntity<PartiesResponseDTO>{
        val partyOptional = partiesService.createParty(
            partiesRequestDTO.name,
            partiesRequestDTO.documentNumber,
            partiesRequestDTO.partyType,
            partiesRequestDTO.caseId,
            partiesRequestDTO.legalRepresentativeId,
            partiesRequestDTO.contact,
        )
        if (partyOptional.isEmpty){
            return ResponseEntity.badRequest().build()
        }
        return ResponseEntity(partiesFactory.createPartyResponse(partyOptional.get()), HttpStatus.OK)
    }

    @PostMapping("/contact")
    fun createContact(@RequestBody partiesContactsRequestDTO: PartiesContactsRequestDTO): ResponseEntity<PartiesContactsResponseDTO>{
        val contactOptional = partiesContactsService.createContact(
            partiesContactsRequestDTO.contactType,
            partiesContactsRequestDTO.contact,
            partiesContactsRequestDTO.isPrimary,
            partiesContactsRequestDTO.partyId,
        )
        if (contactOptional.isEmpty){
            return ResponseEntity.badRequest().build()
        }
        return ResponseEntity(partiesContactsFactory.createContactResponse(contactOptional.get()), HttpStatus.OK)
    }



    @PutMapping("/{id}")
    fun updateParty(@PathVariable id: Int, @RequestBody  partiesRequestDTO: PartiesRequestDTO): ResponseEntity<PartiesResponseDTO>{
        val partyOptional = partiesService.updateParty(id,
            partiesRequestDTO.name,
            partiesRequestDTO.documentNumber,
            partiesRequestDTO.partyType,
            partiesRequestDTO.legalRepresentativeId,
            partiesRequestDTO.contact,
            partiesRequestDTO.caseId,
        )
        if (partyOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity(partiesFactory.createPartyResponse(partyOptional.get()), HttpStatus.OK)
    }

    @PutMapping("/contact/{id}")
    fun updateContact(@PathVariable id: Int, @RequestBody partiesContactsRequestDTO: PartiesContactsRequestDTO): ResponseEntity<PartiesContactsResponseDTO>{
        val party = partiesService.findPartyById(partiesContactsRequestDTO.partyId).orElseThrow { RuntimeException("Caso nao encontrado") }
        val contactOptional = partiesContactsService.updateContact(id, partiesContactsFactory.createContact(
            partiesContactsRequestDTO.contactType,
            partiesContactsRequestDTO.contact,
            partiesContactsRequestDTO.isPrimary,
            party,
        ))
        if (contactOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity(partiesContactsFactory.createContactResponse(contactOptional.get()), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteParty(@PathVariable id: Int): ResponseEntity<PartiesResponseDTO>{
        val partyOptional = partiesService.deleteParty(id)
        if (partyOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity(partiesFactory.createPartyResponse(partyOptional.get()), HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/contact/{id}")
    fun deleteContact(@PathVariable id: Int): ResponseEntity<PartiesContactsResponseDTO>{
        val contactOptional = partiesContactsService.deleteContact(id)
        if (contactOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity(partiesContactsFactory.createContactResponse(contactOptional.get()), HttpStatus.NO_CONTENT)
    }

    @GetMapping("exists/{documentNumber}")
    fun getPartyExistsByDocumentNumber(@PathVariable documentNumber: String): ResponseEntity<Boolean>{
        val exists = partiesService.partyExistsByDocumentNumber(documentNumber)
        return ResponseEntity.ok(exists)
    }

    @GetMapping("documentNumber/{documentNumber}")
    fun getPartyByDocumentNumber(@PathVariable documentNumber: String): ResponseEntity<PartiesResponseDTO>{
        val partyOptional = partiesService.findPartyByDocumentNumber(documentNumber)
        if (partyOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(partiesFactory.createPartyResponse(partyOptional.get()))
    }

    @GetMapping("name/{name}")
    fun getAllpartyByNameContainingIgnoreCase(@PathVariable name: String): ResponseEntity<List<PartiesResponseDTO>>{
        val parties = partiesService.findAllByNameContainingIgnoreCase(name)
        val partyResponseDTOList = parties.map { partiesFactory.createPartyResponse(it) }
        return ResponseEntity.ok(partyResponseDTOList)
    }

    @GetMapping("partyType/{partyType}")
    fun getAllpartyByPartyType(@PathVariable partyType: String): ResponseEntity<List<PartiesResponseDTO>>{
        val parties = partiesService.findAllByPartyType(partyType)
        val partyResponseDTOList = parties.map { partiesFactory.createPartyResponse(it) }
        return ResponseEntity.ok(partyResponseDTOList)
    }

    @GetMapping("cases/{caseId}")
    fun getAllpartyByCaseIdInt(@PathVariable case: Int): ResponseEntity<List<PartiesResponseDTO>>{
        val parties = partiesService.findAllByCaseIdInt(case)
        val partyResponseDTOList = parties.map { partiesFactory.createPartyResponse(it) }
        return ResponseEntity.ok(partyResponseDTOList)
    }


    @GetMapping("/{id}")
    fun getPartyById(@PathVariable id: Int): ResponseEntity<PartiesResponseDTO>{
        val partyOptional = partiesService.findPartyById(id)
        if (partyOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(partiesFactory.createPartyResponse(partyOptional.get()))
    }

    @GetMapping("/contacts/contact/{contact}")
    fun getContactByContact(@PathVariable contact: String): ResponseEntity<PartiesContactsResponseDTO>{
        val contactOptional = partiesContactsService.findPartyByContact(contact = contact)
        if (contactOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(partiesContactsFactory.createContactResponse(contactOptional.get()))
    }

    @GetMapping("/contacts/{id}")
    fun getContactById(@PathVariable id: Int): ResponseEntity<PartiesContactsResponseDTO>{
        val contactOptional = partiesContactsService.findContactById(id)
        if (contactOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(partiesContactsFactory.createContactResponse(contactOptional.get()))
    }

    @GetMapping("/contacts/party/{partyId}")
    fun getAllByPartyId(@PathVariable partyId: Int): ResponseEntity<List<PartiesContactsResponseDTO>>{
        val parties = partiesContactsService.findAllByPartyId_PartyId(partyId)
        val partyResponseDTOList = parties.map { partiesContactsFactory.createContactResponse(it) }
        return ResponseEntity.ok(partyResponseDTOList)
    }

    @GetMapping("/contacts/party/{partyId}/isPrimary")
    fun getAllByPartyIdAndIsPrimaryTrue(@PathVariable partyId: Int): ResponseEntity<List<PartiesContactsResponseDTO>>{
        val parties = partiesContactsService.findByPartyId_PartyIdAndIsPrimaryTrue(partyId)
        val partyResponseDTOList = parties.map { partiesContactsFactory.createContactResponse(it) }
        return ResponseEntity.ok(partyResponseDTOList)
    }

    @GetMapping("/contacts/party/{partyId}/contactType/{contactType}")
    fun getAllByPartyIdAndContactType(@PathVariable partyId: Int, @PathVariable contactType: ContactType): ResponseEntity<List<PartiesContactsResponseDTO>>{
        val parties = partiesContactsService.findAllByPartyId_PartyIdAndContactType(partyId, contactType)
        val partyResponseDTOList = parties.map { partiesContactsFactory.createContactResponse(it) }
        return ResponseEntity.ok(partyResponseDTOList)
    }

    @GetMapping("/contacts/contactType/{contactType}")
    fun getAllByContactType(@PathVariable contactType: ContactType): ResponseEntity<List<PartiesContactsResponseDTO>>{
        val parties = partiesContactsService.findAllByContactType(contactType)
        val partyResponseDTOList = parties.map { partiesContactsFactory.createContactResponse(it) }
        return ResponseEntity.ok(partyResponseDTOList)
    }

    @GetMapping("exists/{contact}")
    fun existsPartyByContact(@PathVariable contact: String): ResponseEntity<Boolean>{
        val exists = partiesContactsService.existsPartyByContact(contact)
        return ResponseEntity.ok(exists)
    }

    @GetMapping
    fun getAllParties(): ResponseEntity<List<PartiesResponseDTO>>{
        val parties = partiesService.findAllParties()
        val partyResponseDTOList = parties.map { partiesFactory.createPartyResponse(it) }
        return ResponseEntity.ok(partyResponseDTOList)
    }

    @GetMapping("/contacts")
    fun getAllContacts(): ResponseEntity<List<PartiesContactsResponseDTO>>{
        val parties = partiesContactsService.findAllContacts()
        val partyResponseDTOList = parties.map { partiesContactsFactory.createContactResponse(it) }
        return ResponseEntity.ok(partyResponseDTOList)
    }
}