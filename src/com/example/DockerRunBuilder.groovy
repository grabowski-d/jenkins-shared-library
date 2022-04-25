package com.example

class DockerRunBuilder {
    String parameters = ""
    String container

    DockerRunBuilder(String containerName) {
        this.container = containerName
    }

    def privileged() {
        parameters += " --privileged"
        return this
    }

    def withMount(String hostPath, String containerPath) {
        parameters += " -v ${hostPath}:${containerPath}"
        return this
    }

    String build() {
        return "docker run" + this.parameters + " " + this.container
    }
}
