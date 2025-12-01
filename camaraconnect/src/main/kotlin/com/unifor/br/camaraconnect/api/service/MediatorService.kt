package com.unifor.br.camaraconnect.api.service

import com.unifor.br.camaraconnect.repository.MediatorRepository
import com.unifor.br.camaraconnect.repository.model.Mediator
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class MediatorService(
    private val  mediatorRepository: MediatorRepository
) {
    fun createMediator(mediator: Mediator): Optional<Mediator>{
        val newMediator = mediatorRepository.save(mediator)
        return Optional.of(newMediator)
    }
    fun updateMediator(id:Int, mediator: Mediator):Optional<Mediator>{
        val mediatorOptional = mediatorRepository.findById(id)
        if (mediatorOptional.isEmpty){
            return Optional.empty()
        }
        val mediatorToUpdate= Mediator(
            mediatorId = mediatorOptional.get().mediatorId,
            registrationNumber = mediator.registrationNumber,
            userId = mediatorOptional.get().userId
        )
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