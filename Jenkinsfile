pipeline {
    agent any
     tools { 
        maven 'Maven 3.9.7' 
    }

    environment {
        
        ARTIFACT_NAME = 'target/*.jar'  // Ou target/*.war, dependendo do seu projeto
        DEPLOY_PATH = '/var/www/html' // Caminho de deploy (exemplo)
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                script {
                    sh 'mvn dependency:resolve'
                    sh 'mvn package'
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    sh 'mvn test'
                    junit 'target/surefire-reports/*.xml' // Publica resultados de testes
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    def artifactPath = findFiles(glob: env.ARTIFACT_NAME)[0].path
                    sh "scp -r ${artifactPath} user@host:${env.DEPLOY_PATH}" // Exemplo de deploy com SCP
                }
            }
        }
    }

    post {
        always {
            cleanWs() // Limpa o workspace após o build
            archiveArtifacts artifacts: env.ARTIFACT_NAME // Arquiva o artefato 
        }
    }
}
