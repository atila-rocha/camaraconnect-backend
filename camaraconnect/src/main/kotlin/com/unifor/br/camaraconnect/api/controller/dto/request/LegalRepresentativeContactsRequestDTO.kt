package com.unifor.br.camaraconnect.api.controller.dto.request

import com.unifor.br.camaraconnect.repository.model.ContactType

data class LegalRepresentativeContactsRequestDTO(
    val contactType: ContactType,
    val contact: String,
    val isPrimary: Boolean = false,
    val legalRepresentativeId: Int
)