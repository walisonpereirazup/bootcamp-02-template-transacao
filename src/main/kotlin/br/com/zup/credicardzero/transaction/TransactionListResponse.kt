package br.com.zup.credicardzero.transaction

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

class TransactionListResponse private constructor(
        val id: UUID,
        val amount: BigDecimal,
        val recipient: RecipientResponse,
        val card: CardResponse,
        val createdAt: LocalDateTime
) {
    class RecipientResponse(val name: String, val city: String, val address: String) {
        constructor(recipient: Recipient) : this(recipient.name, recipient.city, recipient.address)
    }

    class CardResponse(id: UUID, val email: String) {
        constructor(card: Card) : this(card.id, card.email)
    }

    companion object {
        fun of(transactions: MutableList<Transaction>): List<TransactionListResponse> =
                transactions.map {
                    TransactionListResponse(
                            id = it.id,
                            amount = it.amount,
                            recipient = RecipientResponse(it.recipient),
                            card = CardResponse(it.card),
                            createdAt = it.createdAt
                    )
                }
    }
}
