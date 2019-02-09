package my.tocker.kotlinrouter.router

import my.tocker.kotlinrouter.handler.SomeHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class SomeRouter(val someHandler: SomeHandler) {

    @Bean
    fun customerRoutes() = router {
        "/funtional".nest {
            "/cutomer".nest {
                GET("/{id}", someHandler::get)
                POST("/", someHandler::create)
            }
            "/customers".nest {
                GET("/", someHandler::search)
            }
        }
    }

}