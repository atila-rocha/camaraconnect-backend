package com.unifor.br.camaraconnect.api.service

import com.unifor.br.camaraconnect.factory.MediationCaseFactory
import com.unifor.br.camaraconnect.repository.MediationCaseRepository
import com.unifor.br.camaraconnect.repository.MediatorRepository
import com.unifor.br.camaraconnect.repository.PartiesRepository
import com.unifor.br.camaraconnect.repository.model.CaseStatus
import com.unifor.br.camaraconnect.repository.model.MediationCase
import com.unifor.br.camaraconnect.repository.model.Mediator
import com.unifor.br.camaraconnect.repository.model.Parties
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class MediationCaseService (
    private val  mediationcaseRepository: MediationCaseRepository,
    private val mediatorRepository: MediatorRepository,
    private val mediationCaseFactory: MediationCaseFactory,
    private val partiesService: PartiesService,

    ) {
    fun createMediatonCase(
        caseNum: String,
        description: String? = "",
        mediatorId: Int,
    ): Optional<MediationCase>{
        val mediator = mediatorRepository.findById(mediatorId).orElseThrow { RuntimeException("Mediador não encontrado") }
        val saved= mediationCaseFactory.createMediationCase(caseNum, description, mediator)
        val newMediationCase = mediationcaseRepository.save(saved)
        return Optional.of(newMediationCase)
    }
    fun updateMediatonCase(id:Int, caseNum: String, description: String? = "", mediatorId: Int, caseStatus: CaseStatus):Optional<MediationCase>{
        val mediator = mediatorRepository.findById(mediatorId).orElseThrow { RuntimeException("Mediador não encontrado") }
        val mediationOptional = mediationcaseRepository.findById(id).orElseThrow { RuntimeException("Caso não encontrado") }
        val parties = partiesService.findAllByCaseId(mediationOptional).toMutableList()
//        if (mediationOptional.isEmpty){
//            return Optional.empty()
//        }
        val mediatonToUpdate= MediationCase(
            caseId = mediationOptional.caseId,
            caseNum = mediationOptional.caseNum,
            description = description,
            caseStatus = caseStatus,
            mediatorId = mediator,
            partyId = parties
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
        return mediationcaseRepository.findAllByCaseStatusOrderByDatetimeUpdatedAtDesc(caseStatus)
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
    fun countCasesbyCaseStatus(caseStatus: CaseStatus): Long{
        return mediationcaseRepository.countByCaseStatus(caseStatus)
    }

    fun existsByCaseNum(caseNum: String): Boolean{
        return mediationcaseRepository.existsByCaseNum(caseNum)
    }

    fun findMediatonCaseById(id: Int): Optional<MediationCase>{
        val mediatonCaseOptional = mediationcaseRepository.findById(id)
        if (mediatonCaseOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(mediatonCaseOptional.get())
    }
    fun findAllMediationCases(): List<MediationCase>{
        return mediationcaseRepository.findAll()
    }
}