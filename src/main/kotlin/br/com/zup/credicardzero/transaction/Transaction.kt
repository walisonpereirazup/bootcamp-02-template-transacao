package br.com.zup.credicardzero.transaction

import br.com.zup.credicardzero.transaction.Transaction.Companion.LATEST_BY_CARD
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
@NamedQueries(NamedQuery(
        name = LATEST_BY_CARD,
        query = "select t from Transaction t join fetch t.card c where c.id = :card order by t.createdAt desc"
))
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
) {
    companion object {
        const val LATEST_BY_CARD: String = "Transaction.latestByCard"
    }
}
