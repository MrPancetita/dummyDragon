pipeline {
    agent any
    tools {
        jdk 'jdk-17.0.6+10'
    }
    stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', url: 'https://github.com/MrPancetita/dummyDragon'
            }
        }
        stage('Build') {
            steps {
                sh 'chmod 744 ./mvnw'
                sh './mvnw clean package'
            }
            post {
                always {
                    sh 'echo DUMMY: Desplegando a staging...'
                }
                success {
                    sh 'echo creando peticion de cambio'
                    jiraSendDeploymentInfo (
                            environmentId: 'es-prod-1',
                            environmentName: 'es-prod-1',
                            environmentType: 'production',
                            serviceIds: [
                                    'b:YXJpOmNsb3VkOmdyYXBoOjpzZXJ2aWNlL2Y3ZmE1NDJhLTgwYWEtNDA3Zi1iMTY1LTU1ZDBiOTdjNTA1NS8yNTg4MTUxZS1hNjBjLTExZWQtYTk1Yi0xMjhiNDI4MTk0MjQ='
                            ],
                            site: 'serconlo.atlassian.net',
                            state: 'pending'
                    )
                }
            }
        }
        stage('Despliegue Prod') {
            steps {
                input message: 'Please confirm the status of the change associated to this build number ', ok: 'Approved'
            }
            post {
                success {
                    sh 'echo DUMMY: Desplegando en produccion...'
                    jiraSendDeploymentInfo (
                            environmentId: 'es-prod-1',
                            environmentName: 'es-prod-1',
                            environmentType: 'production',
                            serviceIds: [
                                    'b:YXJpOmNsb3VkOmdyYXBoOjpzZXJ2aWNlL2Y3ZmE1NDJhLTgwYWEtNDA3Zi1iMTY1LTU1ZDBiOTdjNTA1NS8yNTg4MTUxZS1hNjBjLTExZWQtYTk1Yi0xMjhiNDI4MTk0MjQ='
                            ],
                            site: 'serconlo.atlassian.net',
                            state: 'success'
                    )
                }
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/TEST-*.xml'
            archiveArtifacts 'target/*.war'
        }
    }
}

/**
pipeline {
    agent any
    stages {
        stage('Build') {
          steps {
            sh 'echo Run build...'
          }
        }
        stage('Test') {
          steps {
            sh 'echo Run tests...'
          }
        }
        stage('Stage') {
          steps {
            sh 'echo Deploy to Staging...'
          }
        }
        stage('Production') {
          steps {
            jiraSendDeploymentInfo (
                environmentId: 'us-prod-1',
                environmentName: 'us-prod-1',
                environmentType: 'production',
                serviceIds: [
                    '<YOUR-SERVICE-ID>'
                ],
                site: '<YOUR-SITE>.atlassian.net',
                state: 'in_progress'
            )
            sh 'echo Deploy to Production starting...'
          }
          post {
              always {
                sh 'sleep 2'
                sh 'echo Deployment to production finished'
              }
              // Notify Jira based on deployment step result
              success {
                echo 'Deployment successful'
                jiraSendDeploymentInfo (
                    environmentId: 'us-prod-1',
                    environmentName: 'us-prod-1',
                    environmentType: 'production',
                    serviceIds: [
                        '<YOUR-SERVICE-ID>'
                    ],
                    site: '<YOUR-SITE>.atlassian.net',
                    state: 'successful'
                )
              }
              failure {
                echo 'Deployment failed'
                jiraSendDeploymentInfo (
                    environmentId: 'us-prod-1',
                    environmentName: 'us-prod-1',
                    environmentType: 'production',
                    serviceIds: [
                        '<YOUR-SERVICE-ID>'
                    ],
                    site: '<YOUR-SITE>.atlassian.net',
                    state: 'failed'
                )

              }
              aborted {
                echo 'Deployment cancelled'
                jiraSendDeploymentInfo (
                    environmentId: 'us-prod-1',
                    environmentName: 'us-prod-1',
                    environmentType: 'production',
                    serviceIds: [
                        '<YOUR-SERVICE-ID>'
                    ],
                    site: '<YOUR-SITE>.atlassian.net',
                    state: 'cancelled'
                )
              }
            }
          }
        }
}
**/
