package br.com.zup.credicardzero

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TransactionApplication

fun main(args: Array<String>) {
	runApplication<TransactionApplication>(*args)
}
