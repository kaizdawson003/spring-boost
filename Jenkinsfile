pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git(
                    url: 'https://github.com/kaizdawson003/spring-boost',
                    branch: 'main',
                    credentialsId: 'github-ssh'
                )
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t phucitdev/spring-boost-deploy:latest .'
            }
       }
       stage('Docker Push') {
            steps {
                withCredentials([
                  usernamePassword(
                  credentialsId: 'dockerhub',
                  usernameVariable: 'DOCKER_USERNAME',
                  passwordVariable: 'DOCKER_PASSWORD'
                  )
                ]) {
            sh '''
                echo "$DOCKER_PASSWORD" | docker login \
                    -u "$DOCKER_USERNAME" \
                    --password-stdin

                docker push phucitdev/spring-boost-deploy:latest
            '''
                   }
             }
        }
        stage('Deploy') {
    steps {
        sshagent(['vps-ssh']) {
            sh '''
                ssh -o StrictHostKeyChecking=no root@134.209.104.47 "
                    cd /opt/spring-boost-deploy &&
                    docker compose pull app &&
                    docker compose up -d app
                "
            '''
        }
    }
}
    }
}