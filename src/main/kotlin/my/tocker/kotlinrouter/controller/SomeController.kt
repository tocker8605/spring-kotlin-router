package my.tocker.kotlinrouter.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SomeController {

    @GetMapping("/test")
    fun get(): String {
        return "hey there"
    }

}