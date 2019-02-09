package my.tocker.kotlinrouter.service

import my.tocker.kotlinrouter.domain.Customer
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Component
class SomeServiceImpl: SomeService {
    companion object {
        val initialCustomers = arrayOf(
            Customer(1, "tocker"),
            Customer(2, "sucker"),
            Customer(3, "fucker", Customer.Telephone("82", "12341234"))
        )
    }

    val customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))

    override fun get(id: Int): Mono<Customer> = customers[id]?.toMono() ?: Mono.empty()

    override fun search(nameFilter: String): Flux<Customer> =
        customers.filter{ it.value.name.contains(nameFilter, true) }
            .map(Map.Entry<Int, Customer>::value).toFlux()

    override fun create(customerMono: Mono<Customer>): Mono<Customer> =
            customerMono.map {
                customers[it.id] = it
                it
            }
}