package br.com.zup.credicardzero.shared

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.converter.ProjectingMessageConverter

@Configuration
class KafkaConfiguration {
    @Bean
    fun projectingConverter(mapper: ObjectMapper) = ProjectingMessageConverter(mapper)
}
