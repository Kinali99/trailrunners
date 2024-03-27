pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Bygg ditt Java-projekt här
                bat 'mvn compile' // Exempel: Om du använder Maven
            }
        }
        stage('Test') {
            steps {
                // Kör JUnit-test här
                bat 'mvn test' // Exempel: Om du använder Maven
            }
            post {
                always {
                    // Arkivera JUnit-testresultat
                    junit 'target/surefire-reports/*/.xml' // Anpassa sökvägen efter dina testresultat
                }
            }
        }
    }
}
