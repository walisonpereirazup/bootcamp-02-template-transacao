package br.com.zup.credicardzero.transaction

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "cards")
data class Card(
        @Id
        val id: UUID,

        @field:NotBlank
        val email: String,
)
