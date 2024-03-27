pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat 'mvn compile' // Bygg ditt Java-projekt här
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test' // Kör JUnit-test här
            }
            post {
                always {
                    junit 'target/surefire-reports/**/*.xml' // Arkivera JUnit-testresultat
                }
            }
        }
    }
}

