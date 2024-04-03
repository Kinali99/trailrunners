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
                sh "mvn test" // Kör JUnit-test här
                junit "target/surefire-reports/**/*.xml"
                
            }
        }
    }
}

