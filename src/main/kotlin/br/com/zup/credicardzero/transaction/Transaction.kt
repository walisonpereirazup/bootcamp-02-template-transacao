package br.com.zup.credicardzero.transaction

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.PastOrPresent
import javax.validation.constraints.Positive

@Entity
@Table(name = "transactions")
data class Transaction(
        @Id
        val id: UUID = UUID.randomUUID(),

        @field:NotNull
        @field:Positive
        val amount: BigDecimal,

        @field:NotNull
        @Valid
        @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        val recipient: Recipient,

        @field:NotNull
        @Valid
        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        val card: Card,

        @field:NotNull
        @field:PastOrPresent
        val createdAt: LocalDateTime
)
