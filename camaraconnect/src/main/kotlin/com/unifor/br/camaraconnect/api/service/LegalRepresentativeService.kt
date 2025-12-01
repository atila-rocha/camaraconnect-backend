package com.unifor.br.camaraconnect.api.service

import com.unifor.br.camaraconnect.repository.LegalRepresentativeRepository
import com.unifor.br.camaraconnect.repository.model.LegalRepresentative
import com.unifor.br.camaraconnect.repository.model.Parties
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class LegalRepresentativeService(
    private val  legalRepresentativeRepository: LegalRepresentativeRepository
) {
        fun createRepresentative(representative: LegalRepresentative): Optional<LegalRepresentative>{
        val newRepresentative = legalRepresentativeRepository.save(representative)
        return Optional.of(newRepresentative)
    }
    fun updateRepresentative(id:Int, representative: LegalRepresentative):Optional<LegalRepresentative>{
        val representativeOptional = legalRepresentativeRepository.findById(id)
        if (representativeOptional.isEmpty){
            return Optional.empty()
        }
        val representativeToUpdate= LegalRepresentative(
            representativeId = representativeOptional.get().representativeId,
            name = representative.name,
            oabNumber = representative.oabNumber,
            oabState = representative.oabState,
            partyId = representative.partyId,
            contact = representative.contact
        )
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

    fun existsRepresentativeByOabNumberAndOabState(oabNumber: String, oabState: String): Optional<Boolean>{
        return legalRepresentativeRepository.existsByOabNumberAndOabState(oabNumber, oabState)
    }

    fun findAllByOabState(oabNumber: String, oabState: String): List<LegalRepresentative>{
        return legalRepresentativeRepository.findAllByOabState(oabNumber, oabState)
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