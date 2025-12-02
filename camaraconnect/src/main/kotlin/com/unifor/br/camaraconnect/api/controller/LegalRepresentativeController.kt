package com.unifor.br.camaraconnect.api.controller

import com.unifor.br.camaraconnect.api.controller.dto.request.LegalRepresentativeContactsRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.request.LegalRepresentativeRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.response.LegalRepresentativeContactsResponseDTO
import com.unifor.br.camaraconnect.api.controller.dto.response.LegalRepresentativeResponseDTO
import com.unifor.br.camaraconnect.api.service.LegalRepresentativeContactsService
import com.unifor.br.camaraconnect.api.service.LegalRepresentativeService
import com.unifor.br.camaraconnect.factory.LegalRepresentativeFactory
import com.unifor.br.camaraconnect.factory.LegalRepresentativeContactsFactory
import com.unifor.br.camaraconnect.repository.model.ContactType
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
    private val legalRepresentativeFactory: LegalRepresentativeFactory,
    private val legalRepresetativeContactsFactory: LegalRepresentativeContactsFactory,

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
        return ResponseEntity(legalRepresentativeFactory.createResponse(representativeOptional.get()), HttpStatus.OK)
    }

    @PostMapping("/contact")
    fun createContact(@RequestBody legalRepresentativeContactsRequestDTO: LegalRepresentativeContactsRequestDTO): ResponseEntity<LegalRepresentativeContactsResponseDTO>{
        val contactOptional = legalRepresentativeContactsService.createContact(
            legalRepresentativeContactsRequestDTO.contactType,
            legalRepresentativeContactsRequestDTO.contact,
            legalRepresentativeContactsRequestDTO.isPrimary,
            legalRepresentativeContactsRequestDTO.legalRepresentativeId,
        )
        if (contactOptional.isEmpty){
            return ResponseEntity.badRequest().build()
        }
        return ResponseEntity(legalRepresetativeContactsFactory.createContactResponse(contactOptional.get()), HttpStatus.OK)
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
        return ResponseEntity(legalRepresentativeFactory.createResponse(representativeOptional.get()), HttpStatus.OK)
    }

    @PutMapping("/contact/{id}")
    fun updateContact(@PathVariable id: Int, @RequestBody legalRepresentativeContactsRequestDTO: LegalRepresentativeContactsRequestDTO): ResponseEntity<LegalRepresentativeContactsResponseDTO>{
        val contactOptional = legalRepresentativeContactsService.updateContact(id,
            legalRepresentativeContactsRequestDTO.contactType,
            legalRepresentativeContactsRequestDTO.contact,
            legalRepresentativeContactsRequestDTO.isPrimary,
            legalRepresentativeContactsRequestDTO.legalRepresentativeId,
        )
        if (contactOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity(legalRepresetativeContactsFactory.createContactResponse(contactOptional.get()), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteRepresentative(@PathVariable id: Int): ResponseEntity<LegalRepresentativeResponseDTO>{
        val representativeOptional = legalRepresentativeService.deleteRepresentative(id)
        if (representativeOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity(legalRepresentativeFactory.createResponse(representativeOptional.get()), HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/contact/{id}")
    fun deleteContact(@PathVariable id: Int): ResponseEntity<LegalRepresentativeContactsResponseDTO>{
        val contactOptional = legalRepresentativeContactsService.deleteContact(id)
        if (contactOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity(legalRepresetativeContactsFactory.createContactResponse(contactOptional.get()), HttpStatus.NO_CONTENT)
    }

    @GetMapping("/{id}")
    fun getRepresentativeById(@PathVariable id: Int): ResponseEntity<LegalRepresentativeResponseDTO>{
        val representativeOptional = legalRepresentativeService.findRepresentativeById(id)
        if (representativeOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(legalRepresentativeFactory.createResponse(representativeOptional.get()))
    }

    @GetMapping("/contacts/{id}")
    fun getContactById(@PathVariable id: Int): ResponseEntity<LegalRepresentativeContactsResponseDTO>{
        val contactOptional = legalRepresentativeContactsService.findContactById(id)
        if (contactOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(legalRepresetativeContactsFactory.createContactResponse(contactOptional.get()))
    }

    @GetMapping("/contacts/representative/{id}")
    fun getContactByLegalRepresentativeIdAndIsPrimaryTrue(@PathVariable id: Int): ResponseEntity<LegalRepresentativeContactsResponseDTO>{
        val contactOptional = legalRepresentativeContactsService.findByLegalRepresentativeId_RepresentativeIdAndIsPrimaryTrue(id)
        if (contactOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(legalRepresetativeContactsFactory.createContactResponse(contactOptional.get()))
    }

    @GetMapping("/contacts/contact/{contact}")
    fun getContactByContact(@PathVariable contact: String): ResponseEntity<LegalRepresentativeContactsResponseDTO>{
        val contactOptional = legalRepresentativeContactsService.findContactByContact(contact)
        if (contactOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(legalRepresetativeContactsFactory.createContactResponse(contactOptional.get()))
    }

    @GetMapping("/contacts/exists/{contact}")
    fun existsContactByContact(@PathVariable contact: String): ResponseEntity<Boolean>{
        val exists = legalRepresentativeContactsService.existsContactByContact(contact)
        return ResponseEntity.ok(exists)
    }

    @GetMapping("/oabNumber/{oabNumber}")
    fun getRepresentativeByOabNumber(@PathVariable oabNumber: String): ResponseEntity<LegalRepresentativeResponseDTO>{
        val representativeOptional = legalRepresentativeService.findRepresentativeByOabNumber(oabNumber)
        if (representativeOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(legalRepresentativeFactory.createResponse(representativeOptional.get()))
    }

    @GetMapping("/oabNumber/{oabNumber}/oabState/{oabState}")
    fun getRepresentativeByOabNumberAndOabState(@PathVariable oabNumber: String, @PathVariable oabState: String): ResponseEntity<LegalRepresentativeResponseDTO>{
        val representativeOptional = legalRepresentativeService.findRepresentativeByOabNumberAndOabState(oabNumber, oabState)
        if (representativeOptional.isEmpty){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(legalRepresentativeFactory.createResponse(representativeOptional.get()))
    }

    @GetMapping("exists/{oabNumber}/oabState/{oabState}")
    fun existsRepresentativeByOabNumberAndOabState(@PathVariable oabNumber: String, @PathVariable oabState: String): ResponseEntity<Boolean>{
        val exists = legalRepresentativeService.existsRepresentativeByOabNumberAndOabState(oabNumber, oabState)
        return ResponseEntity.ok(exists)
    }

    @GetMapping("/all/oabState/{oabState}")
    fun getAllRepresentativesByOabState(@PathVariable oabState: String): ResponseEntity<List<LegalRepresentativeResponseDTO>>{
        val representatives = legalRepresentativeService.findAllByOabState(oabState)
        val representativeResponseDTOList = representatives.map { legalRepresentativeFactory.createResponse(it) }
        return ResponseEntity.ok(representativeResponseDTOList)
    }

    @GetMapping("/all/party/{partyId}")
    fun getAllRepresentativesByPartyId(@PathVariable partyId: Int): ResponseEntity<List<LegalRepresentativeResponseDTO>>{
        val representatives = legalRepresentativeService.findAllByPartyId_PartyId(partyId)
        val representativeResponseDTOList = representatives.map { legalRepresentativeFactory.createResponse(it) }
        return ResponseEntity.ok(representativeResponseDTOList)
    }

    @GetMapping("/all/name/{name}")
    fun getAllRepresentativesByNameContainingIgnoreCase(@PathVariable name: String): ResponseEntity<List<LegalRepresentativeResponseDTO>>{
        val representatives = legalRepresentativeService.findAllByNameContainingIgnoreCase(name)
        val representativeResponseDTOList = representatives.map { legalRepresentativeFactory.createResponse(it) }
        return ResponseEntity.ok(representativeResponseDTOList)
    }

    @GetMapping("/contacts/all/representative/{representativeId}")
    fun getAllContactsByLegalRepresentativeId_RepresentativeId(@PathVariable representativeId: Int): ResponseEntity<List<LegalRepresentativeContactsResponseDTO>>{
        val parties = legalRepresentativeContactsService.findAllByLegalRepresentativeId_RepresentativeId(representativeId)
        val partyResponseDTOList = parties.map { legalRepresetativeContactsFactory.createContactResponse(it) }
        return ResponseEntity.ok(partyResponseDTOList)
    }

    @GetMapping("/contacts/all/type/{contactType}")
    fun getAllContactsByContactType(@PathVariable contactType: ContactType): ResponseEntity<List<LegalRepresentativeContactsResponseDTO>>{
        val parties = legalRepresentativeContactsService.findAllByContactType(contactType)
        val partyResponseDTOList = parties.map { legalRepresetativeContactsFactory.createContactResponse(it) }
        return ResponseEntity.ok(partyResponseDTOList)
    }

    @GetMapping("/contacts/all/representative/{representativeId}/type/{contactType}")
    fun getAllContactsByLegalRepresentativeIdAndContactType(@PathVariable contactType: ContactType, @PathVariable representativeId: Int): ResponseEntity<List<LegalRepresentativeContactsResponseDTO>>{
        val parties = legalRepresentativeContactsService.findAllByLegalRepresentativeId_RepresentativeIdAndContactType(representativeId, contactType)
        val partyResponseDTOList = parties.map { legalRepresetativeContactsFactory.createContactResponse(it) }
        return ResponseEntity.ok(partyResponseDTOList)
    }

    @GetMapping("/all/name/caseactive/{name}")
    fun getAllRepresentativesByName(@PathVariable name: String): ResponseEntity<List<LegalRepresentativeResponseDTO>>{
        val representatives = legalRepresentativeService.findAllByName(name)
        val representativeResponseDTOList = representatives.map { legalRepresentativeFactory.createResponse(it) }
        return ResponseEntity.ok(representativeResponseDTOList)
    }

    @GetMapping
    fun getAllRepresentatives(): ResponseEntity<List<LegalRepresentativeResponseDTO>>{
        val representatives = legalRepresentativeService.findAllRepresentatives()
        val representativeResponseDTOList = representatives.map { legalRepresentativeFactory.createResponse(it) }
        return ResponseEntity.ok(representativeResponseDTOList)
    }

    @GetMapping("/contacts")
    fun getAllContacts(): ResponseEntity<List<LegalRepresentativeContactsResponseDTO>>{
        val parties = legalRepresentativeContactsService.findAllContacts()
        val partyResponseDTOList = parties.map { legalRepresetativeContactsFactory.createContactResponse(it) }
        return ResponseEntity.ok(partyResponseDTOList)
    }

}