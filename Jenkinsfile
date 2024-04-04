pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat 'mvn -f "C:\Users\kinal\.jenkins\workspace\Atakan_Kinali\pom.xml" compile'
            }
        }
        stage('Test') {
            steps {
               bat 'mvn -f "C:\Users\kinal\.jenkins\workspace\Atakan_Kinali\pom.xml" test'
                

                
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

