package com.unifor.br.camaraconnect.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table


@Entity
@Table(name="case_status")
data class CaseStatus(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val statusId: Int,
    val statusName: String,
    @OneToMany(mappedBy = "statusId",
        cascade = [jakarta.persistence.CascadeType.ALL],
        orphanRemoval = true)
    val mediationCase: MediationCase

)