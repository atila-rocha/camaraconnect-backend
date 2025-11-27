package com.unifor.br.camaraconnect.repository

import com.unifor.br.camaraconnect.repository.model.Mediator
import org.springframework.data.jpa.repository.JpaRepository


interface MediatorRepository: JpaRepository<Mediator, Int> {
}