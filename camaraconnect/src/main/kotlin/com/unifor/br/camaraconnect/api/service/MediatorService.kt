package com.unifor.br.camaraconnect.api.service

import com.unifor.br.camaraconnect.factory.MediatorFactory
import com.unifor.br.camaraconnect.repository.MediationCaseRepository
import com.unifor.br.camaraconnect.repository.MediatorRepository
import com.unifor.br.camaraconnect.repository.UserRepository
import com.unifor.br.camaraconnect.repository.model.MediationCase
import com.unifor.br.camaraconnect.repository.model.Mediator
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class MediatorService(
    private val  mediatorRepository: MediatorRepository,
    private val mediatorFactory: MediatorFactory,
    private val userRepository: UserRepository,
    private val mediationCaseRepository: MediationCaseRepository

    ) {
    fun createMediator(
        resgistrationNumber: String,
        userId: Int,
        mediationCases: List<Int> = emptyList()
    ): Optional<Mediator>{
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("Usuário não enciontrado") }
        val mediator= mediatorFactory.createMediator(resgistrationNumber,userId, mediationCases, user)
        val newMediator = mediatorRepository.save(mediator)
        return Optional.of(newMediator)
    }
    fun updateMediator(
        id: Int,
        resgistrationNumber: String,
        userId: Int,
        mediationCases: List<Int> = emptyList()
    ):Optional<Mediator>{
        val mediatorOptional = mediatorRepository.findById(id)
        val cases = mediationCases.map {
            val case = mediationCaseRepository.findById(it).orElseThrow { RuntimeException("Caso não encontrado") }
            case
        }.toMutableList()
        if (mediatorOptional.isEmpty){
            return Optional.empty()
        }

        val mediatorToUpdate= Mediator.Builder()
            .mediatorId(mediatorOptional.get().mediatorId!!)
            .registrationNumber(resgistrationNumber)
            .userId(mediatorOptional.get().userId)
            .mediationCases(cases)
            .build()
        val updatedMediator= mediatorRepository.save(mediatorToUpdate)
        return Optional.of(updatedMediator)
    }
    fun deleteMediator(id: Int): Optional<Mediator>{
        val mediatorOptional = mediatorRepository.findById(id)
        if (mediatorOptional.isEmpty){
            return Optional.empty()
        }
        mediatorRepository.deleteById(id)
        return Optional.of(mediatorOptional.get())
    }
    fun findMediatorByRegistrationNumber(registrationNum: String): Optional<Mediator>{
        val mediatorOptional = mediatorRepository.findMediatorByRegistrationNumber(registrationNum)
        if (mediatorOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(mediatorOptional.get())
    }
    fun findMediatorById(id: Int): Optional<Mediator>{
        val mediatorOptional = mediatorRepository.findById(id)
        if (mediatorOptional.isEmpty){
            return Optional.empty()
        }
        return Optional.of(mediatorOptional.get())
    }
    fun findAllMediators(): List<Mediator>{
        return mediatorRepository.findAll()
    }
}