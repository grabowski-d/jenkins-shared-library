package vars

import helpers.PipelineSpockTestBase

class SamplePipelineTest extends PipelineSpockTestBase {

    def script
    def shMock
    def junitMock

    def setup() {
        script = loadScript("vars/samplePipeline.groovy")
        shMock = Mock(Closure)
        helper.registerAllowedMethod('sh', [String], shMock)

        junitMock = Mock(Closure)
        helper.registerAllowedMethod('junit', [HashMap.class], junitMock)
    }

    def cleanup() {
        printCallStack()
    }

    void 'Success scenario'() {
        when:
        script.call()

        then:
        1 * shMock.call("make test")
        1 * junitMock.call(_)
        assertJobStatusSuccess()
    }

    void 'Some test has failed'() {
        when:
        script.call()

        then:
        1 * shMock.call("make test") >> {
            binding.getVariable('currentBuild').result = 'FAILURE'
        }
        1 * junitMock.call(_)
        assertJobStatusFailure()
    }
}