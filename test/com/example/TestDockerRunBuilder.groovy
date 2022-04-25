package com.example

import spock.lang.Specification


class TestDockerRunBuilder extends Specification {

    String container = "ubuntu:20.04"

    def "should provide proper command"() {
        given:
        def builder = new DockerRunBuilder(container)

        when:
        builder.privileged()

        then:
        assert builder.build() == "docker run --privileged " + container
    }

    def "should support mounting volumes"() {
        given:
        def builder = new DockerRunBuilder(container)

        when:
        builder.withMount('/some/path', '/some/destination')

        then:
        assert builder.build().contains("-v /some/path:/some/destination")
    }

}
