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
                junit 'absolute/path/to/target/surefire-reports/**/*.xml'

                
            }
        }
    }
}

