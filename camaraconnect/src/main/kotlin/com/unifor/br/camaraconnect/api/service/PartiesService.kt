package com.unifor.br.camaraconnect.api.service

import com.unifor.br.camaraconnect.repository.PartiesRepository
import com.unifor.br.camaraconnect.repository.model.MediationCase
import com.unifor.br.camaraconnect.repository.model.Parties
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PartiesService (
    private val  partiesRepository: PartiesRepository
){
    fun createParty(party: Parties): Optional<Parties>{
        val newParty = partiesRepository.save(party)
        return Optional.of(newParty)
    }
    fun updateParty(id:Int, party: Parties):Optional<Parties>{
        val partyOptional = partiesRepository.findById(id)
        if (partyOptional.isEmpty){
            return Optional.empty()
        }
        val partyToUpdate= Parties(
            partyId = partyOptional.get().partyId,
            name = party.name,
            documentNumber = party.documentNumber,
            partyType = party.partyType,
            caseId = partyOptional.get().caseId,
            legalRepresentrativeId = party.legalRepresentrativeId,
            contact = party.contact
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

    fun partyExistsByDocumentNumber(documentNumber: String): Optional<Boolean>{
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