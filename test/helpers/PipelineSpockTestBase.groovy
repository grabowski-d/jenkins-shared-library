package helpers

import com.lesfurets.jenkins.unit.RegressionTest
import spock.lang.Specification

class PipelineSpockTestBase extends Specification  implements RegressionTest {

    /**
     * Delegate to the test helper
     */
    @Delegate DeclarativePipelineBase declarativePipelineBase

    def setup() {

        // Set callstacks path for RegressionTest
        callStackPath = 'pipelineTests/groovy/tests/callstacks/'

        // Create and config the helper
        declarativePipelineBase = new DeclarativePipelineBase()
        declarativePipelineBase.setUp()
    }
}