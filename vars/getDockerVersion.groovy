def call() {
    String output = sh(
        script: 'docker --version',
        label: "Get Docker version",
        returnStdout: true
    ).trim()
    def parts = output.split(' ')
    if(parts.size() > 3)
        return parts[2].replace(",", "");
    return ''
}