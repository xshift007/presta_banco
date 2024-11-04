pipeline {
    agent any
    tools{
        maven "maven"

    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/xshift007/presta_banco/', branch: 'main'
            }
        }
        
        stage("Build JAR File"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/xshift007/presta_banco/']])
                dir("prestabanco"){
                    bat "mvn clean install"
                }
            }
        }
        
        stage("Test"){
            steps{
                dir("prestabanco"){
                    bat "mvn test"
                }
            }
        }        
        stage("Build and Push Docker Image"){
            steps{
                dir("prestabanco"){
                    script{
                         withDockerRegistry(credentialsId: 'docker-credentials'){
                            bat "docker build -t xsh1ft/prestabanco ."
                            bat "docker push xsh1ft/prestabanco"
                        }
                    }
                }
            }
        }
    }
}
