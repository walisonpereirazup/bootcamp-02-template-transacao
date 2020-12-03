package br.com.zup.credicardzero.transaction

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.web.JsonPath
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Component
class KafkaIntegration(val entityManager: EntityManager) {
    private val logger: Logger = LoggerFactory.getLogger(KafkaIntegration::class.java)

    @Transactional
    @KafkaListener(topics = ["\${spring.kafka.topic.transactions}"])
    fun onTransactionAdded(message: TransactionAdded) {
        val card = entityManager.find(Card::class.java, message.card.id)
                ?: Card(id = message.card.id, email = message.card.email)
        val transaction = Transaction(
                id = message.id,
                amount = message.amount,
                recipient = Recipient(
                        name = message.recipient.name,
                        city = message.recipient.city,
                        address = message.recipient.address),
                card = card,
                createdAt = message.createdAt)
        entityManager.persist(transaction)

        logger.info("Creating transaction ${transaction.id} for card ${message.card.id}")
    }

    interface TransactionAdded {
        @get:JsonPath("$.id")
        val id: UUID

        @get:JsonPath("$.valor")
        val amount: BigDecimal

        @get:JsonPath("$.estabelecimento")
        val recipient: Recipient

        @get:JsonPath("$.cartao")
        val card: Card

        @get:JsonPath("$.efetivadaEm")
        val createdAt: LocalDateTime

        interface Recipient {
            @get:JsonPath("$.nome")
            val name: String

            @get:JsonPath("$.cidade")
            val city: String

            @get:JsonPath("$.endereco")
            val address: String
        }

        interface Card {
            @get:JsonPath("$.id")
            val id: UUID

            @get:JsonPath("$.email")
            val email: String
        }
    }
}
