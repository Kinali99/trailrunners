pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat "mvn compile" // Bygg ditt Java-projekt här
            }
        }
        stage('Test') {
            steps {
                bat "mvn test" // Kör JUnit-test här
                junit "${pwd()}/target/surefire-reports/**/*.xml"

                
            }
        }
    }
}

