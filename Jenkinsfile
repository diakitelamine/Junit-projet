pipeline {
    agent {
        label ''
    }
    stages {
        stage('Checkout...') {
            steps {
                checkout scm
            }
        }
        stage("Publish") {
            steps {
                script {
                    echo "Received value is: $params.INPUT_CHOICE"
                    if ("$params.INPUT_CHOICE" == "1") {
                        echo "Simple Clean Install Maven"
                        withMaven(maven: 'maven-3.9.2') {
                            bat 'mvn -Dmaven.test.skip=true clean install'
                        }
                    }
                    if ("$params.INPUT_CHOICE" == "2") {
                        echo "Analyze With SonarQube"
                        withSonarQubeEnv(installationName: 'sonarqube-indyli-server') {
                            bat 'mvn -Dmaven.test.skip=true sonar:sonar -Dsonar.projectKey=junitci-business-teacher -Dsonar.login=squ_4f0952f2a0600f0ee2d0f87ee71376eb3f9c07f1'
                        }
                    }
                }
            }
        }
    }
}
