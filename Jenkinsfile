pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Bygg ditt Java-projekt här
                sh 'mvn compile' // Exempel: Om du använder Maven
            }
        }
        stage('Test') {
            steps {
                // Kör JUnit-test här
                sh 'mvn test' // Exempel: Om du använder Maven
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
