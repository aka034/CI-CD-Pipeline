pipeline {  
    agent any  
    stages {  
            stage ('Git-Checkout') {  
                steps{
                    git url:'https://github.com/aka034/MavenWebProject.git', branch: 'master'
                    echo "Checkout successful";
                } 
            }
            stage ('Compile') {  
                  steps{
                    bat label: '', script: 'mvn compile'
                    echo "test successful";
                    
                } 
            }
            stage ('Build') {  
                  steps{
                    bat label: '', script: 'mvn clean'
                    bat label: '', script: 'mvn package'
                    echo "build successful";
                    
                } 
            }
             stage ('Test') {  
                  steps{
                    bat label: '', script: 'mvn test'
                    echo "test successful";
                } 
            }
            
        stage ('Deploy') {
            steps{
            deploy adapters: [tomcat3(credentialsId: 'tomcat-creds', path: '', url: 'http://16.16.143.200:8080/')], contextPath: 'jenkins_calci', onFailure: false, war: '**/*.war'
             echo "Deploy successful";
            }
        }
        stage ('Monitor') { 
           steps{ 
             echo "Now you can monitor!";
           }
        }
    }
}
