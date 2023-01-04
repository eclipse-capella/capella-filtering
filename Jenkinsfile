pipeline {
	agent {
		label 'migration'
	}
  
	tools {
		maven 'apache-maven-latest'
		jdk 'openjdk-jdk14-latest'
	}
  
	environment {
		BUILD_KEY = "6.0.0"
		CAPELLA_PRODUCT_PATH = "${WORKSPACE}/capella/capella"
		CAPELLA_BRANCH = 'master'
  	}
  
  	stages {
  	
		stage('Generate Target Platform') {
	    	steps {        
	        	script { 
		        	if(github.isPullRequest()){
		        	    github.buildStartedComment()
		        	}
		        	
		        	sh 'env'
		        	sh 'mvn clean verify -f releng/org.polarsys.capella.filtering.update/pom.xml'
	       		}         
	     	}
	    }
	    
    	stage('Build and Package') {
      		steps {
      			script {
      				def customParams = github.isPullRequest() ? '' : '-Psign'
      	    
      	    		sh "mvn -DjavaDocPhase=none ${customParams} clean package -f pom.xml"
	       		}
	     	}
	    }
    
		stage('Deploy to Nightly') {
      		steps {
				script {
		    		def deploymentDirName = 
		    			(github.isPullRequest() ? "${BUILD_KEY}-${BRANCH_NAME}-${BUILD_ID}" : "${BRANCH_NAME}-${BUILD_ID}")
		    			.replaceAll('/','-')		
					
					deployer.addonNightlyDropins("${WORKSPACE}/releng/org.polarsys.capella.filtering.site/target/*-dropins-*.zip", deploymentDirName)
					deployer.addonNightlyUpdateSite("${WORKSPACE}/releng/org.polarsys.capella.filtering.site/target/*-updateSite-*.zip", deploymentDirName)					
					currentBuild.description = "${deploymentDirName} - <a href=\"https://download.eclipse.org/capella/addons/filtering/dropins/nightly/${deploymentDirName}\">drop-in</a> - <a href=\"https://download.eclipse.org/capella/addons/filtering/updates/nightly/${deploymentDirName}\">update-site</a>"
	       		}         
	     	}
	    }
	    
	    stage('Download Capella') {
        	steps {
        		script {
	        		def capellaURL = capella.getDownloadURL("${BUILD_KEY}", 'linux', '')

					sh "curl -k -o capella.tar.gz ${capellaURL}"
					sh "tar xvzf capella.tar.gz"

	       		}         
	     	}
	    }

    	stage('Install test features') {
        	steps {
        		script {
	        		sh "chmod 755 ${CAPELLA_PRODUCT_PATH}"
	        		sh "chmod 755 ${WORKSPACE}/capella/jre/bin/java"
	        		
	        		eclipse.installFeature("${CAPELLA_PRODUCT_PATH}", capella.getTestUpdateSiteURL("${BUILD_KEY}"), 'org.polarsys.capella.test.feature.feature.group')
	        		eclipse.installFeature("${CAPELLA_PRODUCT_PATH}", "file:/${WORKSPACE}/releng/org.polarsys.capella.filtering.site/target/repository/".replace("\\", "/"), 'org.polarsys.capella.filtering.feature.feature.group')
	        		eclipse.installFeature("${CAPELLA_PRODUCT_PATH}", "file:/${WORKSPACE}/releng/org.polarsys.capella.filtering.site/target/repository/".replace("\\", "/"), 'org.polarsys.capella.filtering.tests.feature.feature.group')
	       		}         
	     	}
	    }
	    
    	stage('Run tests') {
        	steps {
        		script {
        			wrap([$class: 'Xvnc', takeScreenshot: false, useXauthority: true]) {
		        		
		        		tester.runUITests("${CAPELLA_PRODUCT_PATH}", 'MainTestSuite', 'org.polarsys.capella.filtering.tests.ju', 
		        			['org.polarsys.capella.filtering.tests.ju.MainTestSuite'])		        			 
	        		}
	        		
	        		tester.publishTests()
				}
			}
		}
		
		stage('Sonar') {
			steps {
				script {
					sonar.runSonar("eclipse_capella-filtering", "eclipse/capella-filtering", 'sonarcloud-token-filtering')
				}
			}
		}
	}
  
	post {
    	always {
       		archiveArtifacts artifacts: '**/*.log, *.log, *.xml, **/*.layout, *.exec'
    	}
    	
    	success  {
    		script {
    			if(github.isPullRequest()){
        			github.buildSuccessfullComment()
        		}
        	}
    	}
    	
	    unstable {
	    	script {
	    		if(github.isPullRequest()){
	        		github.buildUnstableComment()
	        	}
	        }
	    }
    
	    failure {
	    	script {
	    		if(github.isPullRequest()){
	        		github.buildFailedComment()
	        	}
	        }
	    }
	    
	    aborted {
	    	script {
	    		if(github.isPullRequest()){
	        		github.buildAbortedComment()
	        	}
	        }
	    }
	}
}