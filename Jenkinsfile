pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials')
        DOCKERHUB_USERNAME = 'xsh1ft' // Reemplaza con tu usuario de Docker Hub
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/xshift007/presta_banco/', branch: 'main'
            }
        }

        stage('Test Backend') {
            steps {
                dir('prestabanco') {
                    withMaven(maven: 'Maven 3.8.6') {
                        sh 'mvn clean test'
                    }
                }
            }
        }

        stage('Build Backend Image') {
            steps {
                dir('prestabanco') {
                    script {
                        dockerImageBackend = docker.build("${DOCKERHUB_USERNAME}/prestabanco-backend:latest")
                    }
                }
            }
        }

        stage('Push Backend Image') {
            steps {
                script {
                    docker.withRegistry('', "${DOCKERHUB_CREDENTIALS}") {
                        dockerImageBackend.push()
                    }
                }
            }
        }

        stage('Build Frontend Image') {
            steps {
                dir('front-end') {
                    sh 'npm install'
                    sh 'npm run build'
                    script {
                        dockerImageFrontend = docker.build("${DOCKERHUB_USERNAME}/prestabanco-frontend:latest")
                    }
                }
            }
        }

        stage('Push Frontend Image') {
            steps {
                script {
                    docker.withRegistry('', "${DOCKERHUB_CREDENTIALS}") {
                        dockerImageFrontend.push()
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline terminado.'
        }
        success {
            echo 'Pipeline ejecutado con éxito.'
        }
        failure {
            echo 'El Pipeline ha fallado.'
        }
    }
}
