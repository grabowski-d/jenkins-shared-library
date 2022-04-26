package com.example

// inspired by https://www.kabisa.nl/tech/running-multiple-docker-containers-in-parallel-with-jenkins/

class DockerNetwork implements Serializable {
    Object script

    def withNetwork(String parameters, Closure body) {
        assert script
        assert parameters

        String networkId = UUID.randomUUID().toString()
        try {
            script.sh(
                script: "docker network create ${parameters} ${networkId}",
                label: 'Create docker network'
            )
            body.call(networkId)
        } catch(ignored) {
            script.echo "unable to create network"
        }
        finally {
            try {
                script.sh(
                    script: "docker network rm ${networkId}",
                    label: 'Remove docker network'
                )
            } catch (ignored) {}
        }
    }
}
