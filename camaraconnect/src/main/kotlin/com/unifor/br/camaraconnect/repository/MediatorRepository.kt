package com.unifor.br.camaraconnect.repository

import com.unifor.br.camaraconnect.repository.model.Mediator
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional


interface MediatorRepository: JpaRepository<Mediator, Int> {
    fun findMediatorByRegistrationNumber(registrationNumber: String): Optional<Mediator>
}