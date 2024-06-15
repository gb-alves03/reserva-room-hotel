pipeline {
    agent any
    
    tools {
        maven 'Maven-jekins' // Nome da sua instalação do Maven no Jenkins
    }

    environment {
        ARTIFACT_NAME = 'target/*.jar' // Ou target/*.war, dependendo do seu projeto
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
                sh 'mvn -v' // Exibe a versão do Maven em uso
                sh 'mvn dependency:resolve'
                sh 'mvn package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
                junit 'target/surefire-reports/*.xml' 
            }
        }
        stage('Deploy') {
            steps {
                script {
                    def artifactPath = findFiles(glob: env.ARTIFACT_NAME)[0].path
                    sh "scp -r ${artifactPath} user@host:${env.DEPLOY_PATH}" 
                }
            }
        }
    }

    post {
        always {
            cleanWs() 
            archiveArtifacts artifacts: env.ARTIFACT_NAME 
        }
    }
}
