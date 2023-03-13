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
                		sh "docker build -t calculator ."
            		}
        	}
        stage("Docker tag "){
            		steps{
                		sh "docker tag calculator:latest naul23541/calculator:latest"
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
        stage("Docker push") {
             		steps {
                  		sh "docker push naul23541/calculator:latest "
             		}
        	}
        stage("Deploy to staging")
        	{
            		steps{
                		sh " docker run -d --rm -p 8768:8080 naul23541/calculator"
            		}
        	}
        stage("Acceptance test") {
            		steps {
                	sleep 60
                		sh "./gradlew acceptanceTest -Dcalculator.url=http://localhost:8768"
            		}

		}
	post
		{
		    always
		    {
		        sh "docker stop calculator "
		    }
		}

 	}
}
