package com.maja.airmes.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@EntityScan("com.maja.airmes.domain")
@EnableJpaRepositories("com.maja.airmes.beans.repositories")
@SpringBootApplication(scanBasePackages = arrayOf("com.maja.airmes.beans"))
@EnableScheduling
class AirmesApplication

fun main(args: Array<String>) {
    SpringApplication.run(AirmesApplication::class.java, *args)
}
