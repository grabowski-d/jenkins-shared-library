import com.example.DockerNetwork

def call(String parameters, Closure body) {
    script {
        return new DockerNetwork(script: this).withNetwork(parameters, body)
    }
}
