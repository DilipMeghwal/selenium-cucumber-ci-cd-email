#!groovy
pipeline {
    agent any
    stages {
        stage('Test on unix') {
            when {
                expression {
                    isUnix() == true
                }
            }
            steps {
                def dockerHome = tool 'docker'
                def mavenHome = tool 'maven'
                env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
                sh 'docker compose up'
                sh 'mvn clean test -Dremote=true'
                sh 'docker compose down'
            }
        }
        stage('Test on window') {
            when {
                expression {
                    isUnix() == false
                }
            }
            steps {
                bat 'docker-compose up'
                bat 'mvn clean test -Dremote=true'
                bat 'docker-compose down'
            }
        }
        stage('reports') {
            steps {
                cucumber buildStatus: 'null',
                        customCssFiles: '',
                        customJsFiles: '',
                        failedFeaturesNumber: -1,
                        failedScenariosNumber: -1,
                        failedStepsNumber: -1,
                        fileIncludePattern: '**/*.json',
                        pendingStepsNumber: -1,
                        skippedStepsNumber: -1,
                        sortingMethod: 'ALPHABETICAL',
                        undefinedStepsNumber: -1,
                        classifications: [
                                ['key': 'ENVIRONMENT', 'value': 'DEV'],
                                ['key': 'TEST_SUITE', 'value': 'SMOKE']
                        ]
            }
        }
    }
}