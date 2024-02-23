#!groovy
pipeline {
    agent any
    stages {
        stage('Running Tests') {
            steps {
                script {
                    try {
                        if (isUnix() == true) {
                            def dockerHome = tool 'docker'
                            def mavenHome = tool 'maven'
                            env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
                            sh 'docker compose up -d'
                            sh 'mvn clean test -Dremote=true'
                            sh 'docker compose down'
                        } else {
                            bat 'docker-compose up -d'
                            bat 'mvn clean test -Dremote=true'
                            bat 'docker-compose down'
                        }
                    }catch (Exception e){
                        if (isUnix() == true) {
                            sh 'docker compose down'
                        }else{
                            bat 'docker-compose down'
                        }
                        throw e;
                    }
                }
            }
        }
    }
    post {
        always {
            cucumber buildStatus: 'null',
                    failedFeaturesNumber: -1,
                    failedScenariosNumber: -1,
                    failedStepsNumber: -1,
                    fileIncludePattern: '**/cucumber-reports/**.json',
                    pendingStepsNumber: -1,
                    skippedStepsNumber: -1,
                    sortingMethod: 'ALPHABETICAL',
                    undefinedStepsNumber: -1,
                    classifications: [
                            ['key': 'ENVIRONMENT', 'value': params.ENVIRONMENT],
                            ['key': 'TEST_SUITE', 'value': params.TEST_SUITE]
                    ]
        }
    }
}