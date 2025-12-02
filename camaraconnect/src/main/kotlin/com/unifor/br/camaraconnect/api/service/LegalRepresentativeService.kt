package com.unifor.br.camaraconnect.api.service

import com.unifor.br.camaraconnect.api.controller.dto.request.LegalRepresentativeContactsRequestDTO
import com.unifor.br.camaraconnect.api.exception.ResourceNotFoundException
import com.unifor.br.camaraconnect.factory.LegalRepresentativeFactory
import com.unifor.br.camaraconnect.repository.LegalRepresentativeRepository
import com.unifor.br.camaraconnect.repository.PartiesRepository
import com.unifor.br.camaraconnect.repository.model.LegalRepresentative
import com.unifor.br.camaraconnect.repository.model.LegalRepresentativeContacts
import com.unifor.br.camaraconnect.repository.model.Parties
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.Collections.emptyList

@Service
class LegalRepresentativeService(
    private val  legalRepresentativeRepository: LegalRepresentativeRepository,
    private val partiesRepository: PartiesRepository,
    private val legalRepresentativeFactory: LegalRepresentativeFactory,
    ) {
        fun createRepresentative(
            name: String,
            oabNumber: String,
            oabState: String,
            partyId: Int,
            contacts: List<LegalRepresentativeContactsRequestDTO> = emptyList()
        ): Optional<LegalRepresentative>{
            val party = partiesRepository.findById(partyId).orElseThrow { ResourceNotFoundException("Parte não encontrada") }
            val representative = legalRepresentativeFactory.createRepresentative(
                name,
                oabNumber,
                oabState,
                partyId,
                contacts,
                party,
            )
            val newRepresentative = legalRepresentativeRepository.save(representative)
        return Optional.of(newRepresentative)
    }
    fun updateRepresentative(
        id:Int, name: String,
        oabNumber: String,
        oabState: String,
        partyId: Int,
        contacts: List<LegalRepresentativeContactsRequestDTO> = emptyList()):Optional<LegalRepresentative>{
        val representativeOptional = legalRepresentativeRepository.findById(id).orElseThrow { ResourceNotFoundException("Representante não encontrado") }
        //if (representativeOptional.isEmpty()){
        //    return Optional.empty()
        //}
        val party = partiesRepository.findById(partyId).orElseThrow { ResourceNotFoundException("Parte nao encontrada") }
        val contactsEntities = contacts.map {
            val representativeContact= LegalRepresentativeContacts.Builder()
                .contactType(it.contactType)
                .contact(it.contact)
                .isPrimary(it.isPrimary)
                .legalRepresentative(representativeOptional)
                .build()
            representativeContact
        }.toMutableList()
        val representativeToUpdate= LegalRepresentative.Builder()
            .representativeId(id)
            .name(name)
            .oabNumber(oabNumber)
            .oabState(oabState)
            .partyId(party)
            .contact(contactsEntities)
            .build()
        val updatedRepresentative= legalRepresentativeRepository.save(representativeToUpdate)
        return Optional.of(updatedRepresentative)
    }
    fun deleteRepresentative(id: Int): Optional<LegalRepresentative>{
        val representativeOptional = legalRepresentativeRepository.findById(id)
        if (representativeOptional.isEmpty){
            return Optional.empty()
        }
        legalRepresentativeRepository.deleteById(id)
        return Optional.of(representativeOptional.get())
    }

    fun findRepresentativeByOabNumber(oabNumber: String): Optional<LegalRepresentative>{
        val representativeOptional = legalRepresentativeRepository.findByOabNumber(oabNumber)
        if (representativeOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(representativeOptional.get())
    }

    fun findRepresentativeByOabNumberAndOabState(oabNumber: String, oabState: String): Optional<LegalRepresentative>{
        val representativeOptional = legalRepresentativeRepository.findByOabNumberAndOabState(oabNumber, oabState)
        if (representativeOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(representativeOptional.get())
    }

    fun findRepresentativeById(id: Int): Optional<LegalRepresentative>{
        val representativeOptional = legalRepresentativeRepository.findById(id)
        if (representativeOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(representativeOptional.get())
    }

    fun existsRepresentativeByOabNumberAndOabState(oabNumber: String, oabState: String): Boolean{
        return legalRepresentativeRepository.existsByOabNumberAndOabState(oabNumber, oabState)
    }

    fun findAllByOabState(oabState: String): List<LegalRepresentative>{
        return legalRepresentativeRepository.findAllByOabState(oabState)
    }

    fun findAllByPartyId(party: Parties): List<LegalRepresentative>{
        return legalRepresentativeRepository.findAllByPartyId(party)
    }

    fun findAllByPartyId_PartyId(partyId: Int): List<LegalRepresentative>{
        return legalRepresentativeRepository.findAllByPartyId_PartyId(partyId)
    }

    fun findAllByNameContainingIgnoreCase(name: String): List<LegalRepresentative>{
        return legalRepresentativeRepository.findAllByNameContainingIgnoreCase(name)
    }

    fun findAllByName(name: String): List<LegalRepresentative>{
        return legalRepresentativeRepository.findAllByName(name)
    }

    fun findAllRepresentatives(): List<LegalRepresentative>{
        return legalRepresentativeRepository.findAll()
    }
}