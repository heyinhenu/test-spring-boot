NAME = 'test-springboot'
VERSION = '0.0.1-SNAPSHOT'

task :default do
  sh 'rake -T'
end

desc "clean"
task :clean do
  sh 'mvn clean'
end

desc "run (on dev mode)"
task :run do
  sh 'mvn spring-boot:run -Drun.jvmArguments="-Xmx512m -noverify -Drun.mode=dev -Dspring.profiles.active=dev" -Drun.arguments="--spring.profiles.active=dev"'
end

desc "run (on prod mode)"
task :start do
  sh 'mvn spring-boot:run -Drun.jvmArguments="-Xmx1g -noverify -Drun.mode=prod -Dspring.profiles.active=prod"'
end

desc "package"
task :package do
  sh 'mvn clean package'
end

desc "package and run on prod mode"
task :prod => %w[package] do
  sh 'java -Xmx1g -noverify -Drun.mode=prod -Dspring.profiles.active=prod -jar target/test-springboot-0.0.1-SNAPSHOT.jar'
end

desc "test"
task :test => %w[clean] do
  sh 'mvn test'
end

desc "guard"
task :guard do
  sh 'guard'
end

namespace :deps do
  desc "check deps update"
  task "check-update" do
    sh 'mvn versions:display-property-updates'
  end
  
  desc "deps tree"
  task :tree do
    sh 'mvn dependency:tree'
  end
end

namespace :dist do

  desc "dist clean"
  task :clean do 
    sh 'rm -rf dist'
  end
  
  task :prepare do 
    sh 'mkdir -p dist/lib'
    sh 'mkdir -p dist/config'
  end

  desc "dist bin"
  task :bin => %w[package dist:clean dist:prepare] do
    jarfile = "target/#{NAME}-#{VERSION}.jar"
    sh "cp #{jarfile} dist/lib"
    sh 'cp src/main/resources/application.properties dist/config'
    
    start_content = <<-eos
#!/bin/bash

java -Xmx1G -jar lib/#{NAME}-#{VERSION}.jar
    eos
    File.write('dist/start.sh', start_content)
  end
end
