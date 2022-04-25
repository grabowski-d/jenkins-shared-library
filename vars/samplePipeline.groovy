def call() {
    pipeline {
        agent none
        stages {
            stage('Build and test') {
                agent {
                    label 'cpp-node'
                }
                steps {
                    sh 'make test'
                }
                post {
                    always {
                        junit testResults: '**/*.xml', allowEmptyResults: false
                    }
                }
            }
        }
    }
}