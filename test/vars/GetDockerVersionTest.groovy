package vars

import helpers.PipelineSpockTestBase

class GetDockerVersionTest extends PipelineSpockTestBase {
    def script

    def setup() {
        script = loadScript("vars/getDockerVersion.groovy")
    }

    def cleanup() {
        printCallStack()
    }

    void 'Success scenario'() {
        given:
        helper.addShMock('docker --version', 'Docker version 20.10.14, build a224086', 0)
        when:
        def result = script.call()

        then:
        assert result == "20.10.14"
        assertJobStatusSuccess()
    }
}
