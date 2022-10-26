#!groovy
pipeline {
   agent any
   stages {
    stage('Test on unix') {
        when {
            expression {
                isUnix()==true
            }
        }
        steps {
           sh 'mvn clean test'
        }
    }
    stage('Test on window') {
            when {
                expression {
                    isUnix()==false
                }
            }
            steps {
                bat 'mvn clean test'
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
                ['key': 'ENVIRONMENT','value': 'DEV'],
                ['key': 'TEST_SUITE','value': 'SMOKE']
            ]
        }
    }
  }
}