package my.tocker.kotlinrouter.router

import my.tocker.kotlinrouter.handler.SomeHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.toMono

@Component
class SomeRouter(private val someHandler: SomeHandler) {

    @Bean
    fun customerRoutes() = router {
        "/functional".nest {
            "/customer".nest {
                GET("/") {
                    ServerResponse.ok().body("tocker tocker".toMono(), String::class.java)
                }
                GET("/{id}", someHandler::get)
                POST("/", someHandler::create)
            }
            "/customers".nest {
                GET("/", someHandler::search)
            }
        }
    }

}