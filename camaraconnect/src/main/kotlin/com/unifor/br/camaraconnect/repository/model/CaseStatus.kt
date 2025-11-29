//CaseStatus.kt
package com.unifor.br.camaraconnect.repository.model





enum class CaseStatus(val descricao: String) {
    EM_ANDAMENTO("Em Andamento"),
    AGENDADO("Agendado"),
    CONCLUIDO("Concluído");

    companion object {
        fun fromString(value: String): CaseStatus {
            return entries.find {
                it.name.equals(value, ignoreCase = true)
            } ?: throw IllegalArgumentException("Status Inválido: $value")
        }
    }

}