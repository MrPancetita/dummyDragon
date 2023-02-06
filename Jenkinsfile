pipeline {
    agent any

      stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', url: 'https://github.com/MrPancetita/dummyDragon'
            }
        }
        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
            post {
                always {
                    sh 'echo always'
                }
            }
        }

                /**
                jiraSendDeploymentInfo (
                environmentId: 'us-prod-1',
                environmentName: 'us-prod-1',
                environmentType: 'staging',
                serviceIds: [
                    'b:YXJpOmNsb3VkOmdyYXBoOjpzZXJ2aWNlL2Y3ZmE1NDJhLTgwYWEtNDA3Zi1iMTY1LTU1ZDBiOTdjNTA1NS8yNTg4MTUxZS1hNjBjLTExZWQtYTk1Yi0xMjhiNDI4MTk0MjQ='
                ],
                site: 'serconlo.atlassian.net',
                state: 'in_progress'
                )
                **/
                echo (jiraSearch 'dummyDragon-pipe').count


                // Run Maven on a Unix agent.
                //sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    sh 'echo success'
                    //junit '**/target/surefire-reports/TEST-*.xml'
                    //archiveArtifacts 'target/*.jar'
                }
            }
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

pipeline {
    agent any
    triggers { pollSCM('* * * * *') }
    stages {
        // implicit checkout stage

        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }
    }
    // post after stages, for entire pipeline, is also an implicit step albeit with explicit config here, unlike implicit checkout stage
    post {
        always {
            junit '**/target/surefire-reports/TEST-*.xml'
            archiveArtifacts 'target/*.jar'
        }
    }
}
Footer
