pipeline {
    agent any

    tools {
        maven 'Maven-jekins' // Nome da instalação do Maven no Jenkins
    }

    environment {
        ARTIFACT_NAME = 'target/*.jar' // Ou target/*.war, dependendo do projeto
        DEPLOY_PATH = '\\\\servidor\\caminho\\para\\deploy' // Caminho UNC para o servidor Windows
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                bat 'mvn -v' 
                bat 'mvn dependency:resolve'
                bat 'mvn package'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
                junit 'target/surefire-reports/*.xml'
            }
        }
        stage('Deploy') {
            steps {
                powershell """
                $artifactPath = (Get-ChildItem -Path env:ARTIFACT_NAME -Recurse).FullName
                Copy-Item $artifactPath -Destination env:DEPLOY_PATH
                """
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
