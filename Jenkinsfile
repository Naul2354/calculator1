pipeline {
 	agent any
 	environment {
    		DOCKERHUB_CREDENTIALS=credentials('dockerhub')
    	}

	 triggers {
 		pollSCM('* * * * *')
	}
 	stages {
 		stage("Compile") {
 			steps {
 			    echo 'Compile...'
 				sh "./gradlew compileJava"
 			}
 		}
 		stage("Unit test") {
 			steps {
 			    echo 'Testing...'
 				sh "./gradlew test"
 			}
 		}
		stage("Code coverage") {
 			steps {
 				sh "./gradlew jacocoTestReport"
 				sh "./gradlew jacocoTestCoverageVerification"
			 }
		}
		stage("Static code analysis") {
 			steps {
 				sh "./gradlew checkstyleMain"
 			}
		}
		stage("Package") {
            steps {
                sh "./gradlew build"
            }
        }
        stage("Docker build") {
            steps {
                sh "docker build -t calculator ."
            }
        }
        stage("Docker login") {
             steps {
                  withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'dockerhub',
                           usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                       sh "docker login --username $USERNAME --password $PASSWORD"
                  }
             }
        }
        stage("Docker push"){
            steps{
                sh "docker push calculator"
            }

        }
 	}
}
