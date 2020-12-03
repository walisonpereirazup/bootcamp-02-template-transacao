package br.com.zup.credicardzero.transaction

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "recipients")
data class Recipient(
        @Id
        val id: UUID = UUID.randomUUID(),

        @field:NotBlank
        val name: String,

        @field:NotBlank
        val city: String,

        @field:NotBlank
        val address: String
)
