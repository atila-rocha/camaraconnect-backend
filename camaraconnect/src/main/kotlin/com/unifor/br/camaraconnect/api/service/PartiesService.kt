package com.unifor.br.camaraconnect.api.service

import com.unifor.br.camaraconnect.api.controller.dto.request.LegalRepresentativeRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.request.PartiesContactsRequestDTO
import com.unifor.br.camaraconnect.api.exception.ResourceNotFoundException
import com.unifor.br.camaraconnect.factory.PartiesFactory
import com.unifor.br.camaraconnect.repository.MediationCaseRepository
import com.unifor.br.camaraconnect.repository.PartiesRepository
import com.unifor.br.camaraconnect.repository.model.LegalRepresentative
import com.unifor.br.camaraconnect.repository.model.LegalRepresentativeContacts
import com.unifor.br.camaraconnect.repository.model.MediationCase
import com.unifor.br.camaraconnect.repository.model.Parties
import com.unifor.br.camaraconnect.repository.model.PartiesContacts
import org.springframework.stereotype.Service
import java.util.Collections
import java.util.Optional

@Service
class PartiesService (
    private val  partiesRepository: PartiesRepository,
    private val partiesFactory: PartiesFactory,
    private val mediationCaseRepository: MediationCaseRepository
){
    fun createParty(
        name: String,
        documentNumber: String,
        partyType: String,
        caseId: Int,
        legalRepresentativeId: MutableList<LegalRepresentativeRequestDTO> = Collections.emptyList(),
        contact: MutableList<PartiesContactsRequestDTO> = Collections.emptyList()
    ): Optional<Parties>{
        val mediationCase = mediationCaseRepository.findById(caseId).orElseThrow { ResourceNotFoundException("Cas nao encontrado") }
        val creatingParty = partiesFactory.createParty(name, documentNumber, partyType, legalRepresentativeId, contact, mediationCase)
        val newParty = partiesRepository.save(creatingParty)
        return Optional.of(newParty)
    }

    fun updateParty(id:Int, name: String, documentNumber: String, partyType: String, legalRepresentativeId: MutableList<LegalRepresentativeRequestDTO>, contact: MutableList<PartiesContactsRequestDTO>,  caseid: Int):Optional<Parties>{
        val partyOptional = partiesRepository.findById(id)
        val mediationCase= mediationCaseRepository.findById(caseid).orElseThrow { ResourceNotFoundException("Mediação não encontrada") }
        if (partyOptional.isEmpty){
            return Optional.empty()
        }
        val party = Parties.Builder()
            .partyId(id)
            .name(name)
            .documentNumber(documentNumber)
            .partyType(partyType)
            .caseId(mediationCase)
            .build()

        party.contact = contact.map {
            PartiesContacts.Builder()
                .contactType(it.contactType)
                .contact(it.contact)
                .isPrimary(it.isPrimary)
                .partyId(party)
                .build()
        }.toMutableList()

        party.legalRepresentrativeId= legalRepresentativeId.map {
            val representative = LegalRepresentative.Builder()
                .name(it.name)
                .oabNumber(it.oabNumber)
                .oabState(it.oabState)
                .partyId(party)
                .build()

            val representativeContacts = it.contacts.map {
                LegalRepresentativeContacts.Builder()
                    .contactType(it.contactType)
                    .contact(it.contact)
                    .isPrimary(it.isPrimary)
                    .legalRepresentative(representative)
                    .build()
            }.toMutableList()

            representative.contact=representativeContacts

            representative

        }.toMutableList()
        val updatedMediator= partiesRepository.save(party)
        return Optional.of(updatedMediator)
    }
    fun deleteParty(id: Int): Optional<Parties>{
        val partyOptional = partiesRepository.findById(id)
        if (partyOptional.isEmpty){
            return Optional.empty()
        }
        partiesRepository.deleteById(id)
        return Optional.of(partyOptional.get())
    }

    fun findPartyByDocumentNumber(documentNumber: String): Optional<Parties>{
        val partyOptional = partiesRepository.findByDocumentNumber(documentNumber)
        if (partyOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(partyOptional.get())
    }

    fun partyExistsByDocumentNumber(documentNumber: String): Boolean{
        return partiesRepository.existsByDocumentNumber(documentNumber)
    }

    fun findAllByNameContainingIgnoreCase(name: String): List<Parties>{
        return partiesRepository.findAllByNameContainingIgnoreCase(name)
    }

    fun findAllByPartyType(partyType: String): List<Parties>{
        return partiesRepository.findAllByPartyType(partyType)
    }

    fun findAllByCaseId(case: MediationCase): List<Parties>{
        return partiesRepository.findAllByCaseId(case)
    }

    fun findAllByCaseIdInt(case: Int): List<Parties>{
        return partiesRepository.findAllByCaseId_CaseId(case)
    }

    fun findPartyById(id: Int): Optional<Parties>{
        val partyOptional = partiesRepository.findById(id)
        if (partyOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(partyOptional.get())
    }
    fun findAllParties(): List<Parties>{
        return partiesRepository.findAll()
    }

}