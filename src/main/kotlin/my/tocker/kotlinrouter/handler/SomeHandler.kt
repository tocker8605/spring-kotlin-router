package my.tocker.kotlinrouter.handler

import my.tocker.kotlinrouter.domain.Customer
import my.tocker.kotlinrouter.service.SomeService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.ServerResponse.status
import org.springframework.web.reactive.function.server.bodyToMono
import java.net.URI

@Component
class SomeHandler(val someService: SomeService) {

    fun get(serverRequest: ServerRequest) =
        someService.get(serverRequest.pathVariable("id").toInt())
            .flatMap { ok().body(fromObject(it)) }
            .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    fun search(serverRequest: ServerRequest) =
            ok().body(someService.search(nameFilter = serverRequest.queryParam("nameFilter").orElse("")), Customer::class.java)

    fun create(serverRequest: ServerRequest) =
            someService.create(serverRequest.bodyToMono()).flatMap {
                created(URI.create("/functional/customer/${it.id}")).build()
            }
}
