pipeline {
    agent any
    stages {
         stage('Build and Test') {
            steps {
                // Bygger projektet och kör testerna
                script {
                    sh 'mvn clean test'
                }
            }
        }
 
        stage('Publish Test Results') {
            steps {
                // Publicerar testresultaten i JUnit XML-format
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }
}
