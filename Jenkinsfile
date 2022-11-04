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
                sh "docker version"
                sh "docker build -t calculator1 ."
            }
        }
        stage("Docker tag "){
            steps{
                sh "docker tag calculator1:latest naul23541/calculator1:latest"
            }
        }
        stage("Docker push") {
             steps {
                  sh "docker push naul23541/calculator1:latest "
             }
        }
        stage("Deploy to staging")
        {
            steps{
                sh " docker run -d --rm -p 8766:8080 --name calculator1 naul23541/calculator1"
            }
        }
        stage("Acceptance test") {
            steps {
                sleep 60
                sh "./gradlew acceptanceTest -Dcalculator.url=http://localhost:8766"
            }
        }
        stage("Stop containter"){
            steps{
                sh "systemctl restart docker.socket docker.service"
                sh "docker stop calculator1 "
            }
        }

 	}

}
