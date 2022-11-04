pipeline {
     agent any
     stages {
          stage("Compile") {
               steps {
                    sh "./gradlew compileJava"
               }
          }
          stage("Unit test") {
               steps {
                    sh "./gradlew test"
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
          stage("Docker push") {
               steps {
                    sh "docker push calculator"
               }
          }

          stage("Deploy to staging") {
               steps {
                    sh "docker run -d --rm -p 8767:080 --name calculator calculator"
               }
          }
          stage("Acceptance test") {
               steps {
                    sleep 60
                    sh "./gradlew acceptanceTest -Dcalculator.url=http://localhost:8767"
               }
          }
     }
     post {
          always {
               sh "docker stop calculator"
          }
     }
}
