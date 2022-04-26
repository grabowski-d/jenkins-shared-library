package vars

import helpers.PipelineSpockTestBase

class WithDockerNetworkTest extends PipelineSpockTestBase {
    def script

    def setup() {
        script = loadScript("vars/withDockerNetwork.groovy")
    }

    def cleanup() {
        printCallStack()
    }

    void 'Success scenario'() {
        given:
        def mockToBeCalled = Mock(Closure)

        when:
        script.call("--ipv6", mockToBeCalled)

        then:
        1 * mockToBeCalled.call(_)
        assertJobStatusSuccess()
    }
}
