package my.tocker.kotlinrouter.service

import my.tocker.kotlinrouter.domain.Customer
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface SomeService {
    fun get(id: Int): Mono<Customer>
    fun search(nameFilter: String): Flux<Customer>
    fun create(customerMono: Mono<Customer>): Mono<Customer>
}