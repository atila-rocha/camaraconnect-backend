package com.unifor.br.camaraconnect.repository

import com.unifor.br.camaraconnect.repository.model.LegalRepresentative
import com.unifor.br.camaraconnect.repository.model.Parties
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional


interface LegalRepresentativeRepository: JpaRepository<LegalRepresentative, Int> {
    fun findByOabNumber(oabNumber: String): Optional<LegalRepresentative>
    fun findByOabNumberAndOabState(oabNumber: String, oabState: String): Optional<LegalRepresentative>
    fun existsByOabNumberAndOabState(oabNumber: String, oabState: String): Optional<Boolean>
    fun findAllByOabState(oabState: String): List<LegalRepresentative>
    fun findAllByPartyId(party: Parties): List<LegalRepresentative>
    fun findAllByPartyId_PartyId(partyId: Int): List<LegalRepresentative>
    fun findAllByNameContainingIgnoreCase(name: String): List<LegalRepresentative>
    fun findAllByName(name: String): List<LegalRepresentative>

}