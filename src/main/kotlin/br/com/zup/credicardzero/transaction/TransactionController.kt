package br.com.zup.credicardzero.transaction

import br.com.zup.credicardzero.transaction.Transaction.Companion.LATEST_BY_CARD
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.*
import javax.persistence.EntityManager

@RestController
class TransactionController(val entityManager: EntityManager) {

    @GetMapping("transactions/{card}")
    fun retrieve(@PathVariable card: UUID): ResponseEntity<*> {
        val transactions = entityManager
                .createNamedQuery(LATEST_BY_CARD, Transaction::class.java)
                .setParameter("card", card)
                .setMaxResults(10)
                .resultList

        if (transactions.isEmpty())
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "card number not found")

        return ResponseEntity.ok(TransactionListResponse.of(transactions))
    }

}
