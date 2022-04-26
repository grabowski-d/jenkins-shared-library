pipeline {
    agent any

    stages {
        stage('checkout') {
            steps {
                git 'https://github.com/grabowski-d/jenkins-shared-library.git'
            }
        }
         stage('run tests') {
            steps {
                script {
                    docker.image('gradle:7.4.2-jdk8').inside { 
                        sh 'gradle --version'
                        sh 'gradle test'
                        junit '**/TEST*.xml'
                    }
                }
            }
        }
    }
}

