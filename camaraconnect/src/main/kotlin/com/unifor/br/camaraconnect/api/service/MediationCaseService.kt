package com.unifor.br.camaraconnect.api.service

import com.unifor.br.camaraconnect.repository.MediationCaseRepository
import com.unifor.br.camaraconnect.repository.model.CaseStatus
import com.unifor.br.camaraconnect.repository.model.MediationCase
import com.unifor.br.camaraconnect.repository.model.Mediator
import com.unifor.br.camaraconnect.repository.model.Parties
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class MediationCaseService (
    private val  mediationcaseRepository: MediationCaseRepository
) {
    fun createMediatonCase(mediationcase: MediationCase): Optional<MediationCase>{
        val newMediationCase = mediationcaseRepository.save(mediationcase)
        return Optional.of(newMediationCase)
    }
    fun updateMediatonCase(id:Int, mediationCase: MediationCase):Optional<MediationCase>{
        val mediationOptional = mediationcaseRepository.findById(id)
        if (mediationOptional.isEmpty){
            return Optional.empty()
        }
        val mediatonToUpdate= MediationCase(
            caseId = mediationOptional.get().caseId,
            caseNum = mediationOptional.get().caseNum,
            description = mediationCase.description,
            caseStatus = mediationCase.caseStatus,
            mediatorId = mediationCase.mediatorId,
            partyId = mediationCase.partyId
        )
        val updatedMediaton= mediationcaseRepository.save(mediatonToUpdate)
        return Optional.of(updatedMediaton)
    }
    fun deleteMediatonCase(id: Int): Optional<MediationCase>{
        val mediatonCaseOptional = mediationcaseRepository.findById(id)
        if (mediatonCaseOptional.isEmpty){
            return Optional.empty()
        }
        mediationcaseRepository.deleteById(id)
        return Optional.of(mediatonCaseOptional.get())
    }

    fun findMediationCaseByCasenum(caseNum: String): Optional<MediationCase>{
        val mediatonCaseOptional = mediationcaseRepository.findByCaseNum(caseNum)
        if (mediatonCaseOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(mediatonCaseOptional.get())
    }

    fun findMediationCaseByCaseStatus(caseStatus: CaseStatus): List<MediationCase>{
        return mediationcaseRepository.findAllByCaseStatus(caseStatus)
    }

    fun findMediationCaseByCaseStatusOrderByCreatedAtDesc(caseStatus: CaseStatus): List<MediationCase>{
        return mediationcaseRepository.findAllByCaseStatusOrderByDatetimeCreatedAtDesc(caseStatus)
    }

    fun findMediationCaseByCaseStatusOrderByUpdatedAtDesc(caseStatus: CaseStatus): List<MediationCase>{
        return mediationcaseRepository.findByAllCaseStatusOrderByDatetimeUpdatedAtDesc(caseStatus)
    }

    fun findAllByMediatorId(mediator: Mediator): List<MediationCase>{
        return mediationcaseRepository.findAllByMediatorId(mediator)
    }

    fun findAllByMediatorIdInt(mediator: Int): List<MediationCase>{
        return mediationcaseRepository.findAllByMediatorId_MediatorId(mediator)
    }

    fun findAllByMediatorIdAndCaseStatus(mediator: Mediator, caseStatus: CaseStatus): List<MediationCase>{
        return mediationcaseRepository.findAllByMediatorIdAndCaseStatus(mediator, caseStatus)
    }

    fun findAllByMediatorIdAndCaseStatusInt(mediator: Int, caseStatus: CaseStatus): List<MediationCase>{
        return mediationcaseRepository.findAllByMediatorId_MediatorIdAndCaseStatus(mediator, caseStatus)
    }

    fun findAllByPartyId(party: Parties): List<MediationCase>{
        return mediationcaseRepository.findAllByPartyId(party)
    }

    fun findAllByPartyIdInt(party: Int): List<MediationCase>{
        return mediationcaseRepository.findAllByPartyId_PartyId(party)
    }

    fun findAllByPartyName(name: String): List<MediationCase>{
        return mediationcaseRepository.findAllByPartyId_NameContainingIgnoreCase(name)
    }
    fun countCasesbyCaseStatus(caseStatus: CaseStatus): Optional<Long>{
        return mediationcaseRepository.countByCaseStatus(caseStatus)
    }

    fun existsByCaseNum(caseNum: String): Optional<Boolean>{
        return mediationcaseRepository.existsByCaseNum(caseNum)
    }

    fun findMediatonCaseById(id: Int): Optional<MediationCase>{
        val mediatonCaseOptional = mediationcaseRepository.findById(id)
        if (mediatonCaseOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(mediatonCaseOptional.get())
    }
    fun findAllMediatornCases(): List<MediationCase>{
        return mediationcaseRepository.findAll()
    }
}