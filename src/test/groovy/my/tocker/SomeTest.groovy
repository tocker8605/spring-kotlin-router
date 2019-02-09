package my.tocker

import spock.lang.Specification

class SomeTest extends Specification {

    def "어떤 테스트"() {
        when:
        def a = 1
        def b = 1

        then:
        a == b
    }

    def "망한 테스트"() {
        when:
        def a = 1
        def b = 1
//        def b = 2

        then:
        a == b
    }

}
