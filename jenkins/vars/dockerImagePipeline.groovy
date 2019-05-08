def call(applicationName){

  def metricsFactory = new com.lastminute.jenkins.metrics.MetricsFactory(jenkins.metrics.api.Metrics, env.NODE_NAME, applicationName)
  def totalDockerTimer = metricsFactory.timer("image", "total")

  stage('Checkout Repo'){
    def checkoutImageTimer = metricsFactory.timer("image", "gitCheckout")
    deleteDir()
    git credentialsId: 'git-read-only', url: 'https://git.company.com/namespace/repo'
    checkoutImageTimer.stop()
  }

  stage('Download Artifact'){
    def artifactTimer = metricsFactory.timer("image", "artifactDownload")
    sh """
      curl -Lo artifact.tgz 'https://artifacts.comapny.com/search?name=${applicationName}&version=latest'
    """
    artifactTimer.stop()
  }

  stage('Docker Image') {
    def dockerBuild = metricsFactory.timer("image", "dockerBuild")
    sh """
      docker build -t docker.company.com/${applicationName}:latest .
    """
    dockerBuild.stop()

    def dockerPush = metricsFactory.timer("image", "dockerPush")
    sh """
      docker push docker.company.com/${applicationName}:latest
    """
    dockerPush.stop()
  }

  totalDockerTimer.stop()
}