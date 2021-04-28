pipeline {
    environment {
        registry = "humahmed1/repository"
        registryCredential = 'dockerhub'
    }
    agent any
    tools {
        maven '3.6.3'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package -f cmp-mock-customer-es/pom.xml'
            }
        }
        stage('Unit Test') {
            steps {
                sh 'mvn -Dtest=RetailCustomerApplicationTest test -f cmp-mock-customer-es/pom.xml'
            }
            post {
                always {
                    junit 'cmp-mock-customer-es/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Build and Deploy Image') {
            steps {
                sh 'mvn spring-boot:build-image -DskipTests=true -f cmp-mock-customer-es/pom.xml'
                sh 'docker run -d -p 8070:8070 -t cmp:0.0.1-SNAPSHOT'
                }
        }
        stage('Integration Test') {
            steps{
                sh 'mvn -Dtest=api-automation/src/test/java/com/example/es/EsKarateRunner -DfailIfNoTests=false test -f api-automation/pom.xml'
            }
            post {
                    always {
                        cucumber '**/cucumber.json'
                    }
                }
        }
        stage('Stop Image Running') {
            steps{
                sh 'docker image rm image cmp:0.0.1-SNAPSHOT'
            }
        }
    }
}