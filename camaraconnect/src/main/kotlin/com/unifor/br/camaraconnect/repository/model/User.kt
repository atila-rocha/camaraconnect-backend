//User.kt
package com.unifor.br.camaraconnect.repository.model

import jakarta.persistence.Entity
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.GeneratedValue
import jakarta.persistence.OneToOne
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User private constructor(
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val userId: Int? = null,
    var username: String,
    var password: String,
    var email: String,
    val role: String?="MEDIATOR", //verificar
    @CreationTimestamp
    val datetimeCreatedAt: LocalDateTime= LocalDateTime.now(),
    @UpdateTimestamp
    var datetimeUpdatedAt: LocalDateTime= LocalDateTime.now(),
    @OneToOne(mappedBy = "userId",
        cascade = [jakarta.persistence.CascadeType.ALL],
        orphanRemoval = true)
    val mediators: Mediator?= null
){
    class Builder{
        private var userId: Int? = null
        private var username: String = ""
        private var password: String = ""
        private var email: String = ""
        private var role: String = "MEDIATOR"
        private var mediators: Mediator ?= null

        fun userId(userId: Int)= apply { this.userId=userId }
        fun username(username: String)= apply { this.username=username }
        fun password(password: String)= apply { this.password=password }
        fun email(email: String)= apply { this.email=email }
        fun role(role: String)= apply { this.role=role }
        fun mediators(mediators: Mediator)= apply { this.mediators=mediators }

        fun build(): User{
            return User(
                username = username,
                password = password,
                email = email,
                role = role,
                mediators = mediators
            )
        }
    }
}