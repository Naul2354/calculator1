pipeline {
 	agent any

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

                sh "docker build -t calculator1 ."
            }
        }
        stage("Docker tag "){
            steps{
                sh "docker tag calculator1:latest naul23541/calculator1:latest"
            }
        }
        stage("Docker login") {
            steps {
                 sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage("Docker push") {
             steps {
                  sh "docker push naul23541/calculator1:latest "
             }
        }


 	}
}
