package com.unifor.br.camaraconnect.api.service

import com.unifor.br.camaraconnect.api.controller.dto.request.LegalRepresentativeRequestDTO
import com.unifor.br.camaraconnect.api.controller.dto.request.PartiesContactsRequestDTO
import com.unifor.br.camaraconnect.factory.PartiesFactory
import com.unifor.br.camaraconnect.repository.MediationCaseRepository
import com.unifor.br.camaraconnect.repository.PartiesRepository
import com.unifor.br.camaraconnect.repository.model.MediationCase
import com.unifor.br.camaraconnect.repository.model.Parties
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
        val mediationCase = mediationCaseRepository.findById(caseId).orElseThrow { RuntimeException("Cas nao encontrado") }
        val creatingParty = partiesFactory.createParty(name, documentNumber, partyType, legalRepresentativeId, contact, mediationCase)
        val newParty = partiesRepository.save(creatingParty)
        return Optional.of(newParty)
    }

    fun updateParty(id:Int, name: String, documentNumber: String, partyType: String, legalRepresentativeId: MutableList<LegalRepresentativeRequestDTO>, contact: MutableList<PartiesContactsRequestDTO>,  caseid: Int):Optional<Parties>{
        val partyOptional = partiesRepository.findById(id)
        val mediationCase= mediationCaseRepository.findById(caseid).orElseThrow { RuntimeException("Mediação não encontrada") }
        if (partyOptional.isEmpty){
            return Optional.empty()
        }
        val partyToUpdate= partiesFactory.createParty(
            name,
            documentNumber,
            partyType,
            legalRepresentativeId,
            contact,
            mediationCase
        )
        val updatedMediator= partiesRepository.save(partyToUpdate)
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