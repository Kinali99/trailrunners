pipeline {
    agent any
    stages {
        stage('Build test hamid') {
            steps {
                script {
                    sh 'mvn compile'
                }
            }
        }
 
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
 
        stage('Post Test') {
            steps {
                script {
                   
                    junit '**/TEST*.xml'
                }
            }
        }
 
    }
}
