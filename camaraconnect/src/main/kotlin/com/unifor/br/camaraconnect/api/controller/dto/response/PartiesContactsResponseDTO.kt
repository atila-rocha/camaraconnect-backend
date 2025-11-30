package com.unifor.br.camaraconnect.api.controller.dto.response

import com.unifor.br.camaraconnect.repository.model.ContactType
import java.time.LocalDateTime

data class PartiesContactsResponseDTO (
    val contactId: Int,
    val contactType: ContactType,
    val contact: String,
    val isPrimary: Boolean,
    val datetimeCreatedAt: LocalDateTime,
    val datetimeUpdatedAt: LocalDateTime,
)