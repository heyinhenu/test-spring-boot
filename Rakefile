NAME = 'test-springboot'
VERSION = '0.0.1-SNAPSHOT'

task :default do
  sh 'rake -T'
end

desc "clean"
task :clean do
  sh 'mvn clean'
end

namespace :server do
  desc "run (on dev mode)"
  task :run do
    Thread.new do
      sh 'guard'
    end
    # sh 'mvn spring-boot:run -Drun.jvmArguments="-Xmx512m -noverify -Drun.mode=dev -Dspring.profiles.active=dev" -Drun.arguments="--spring.profiles.active=dev"'
    sh 'mvn spring-boot:run -Drun.jvmArguments="-Xmx512m -noverify -Drun.mode=dev"'
  end
  
  desc "run (on prod mode)"
  task :start do
    sh 'mvn spring-boot:run -Drun.jvmArguments="-Xmx1g -noverify -Drun.mode=prod"'
  end
end

desc "package"
task :package do
  sh 'mvn clean package'
end

desc "package and run on prod mode"
task :prod => %w[package] do
  # sh 'java -Xmx1g -noverify -Drun.mode=prod -Dspring.profiles.active=prod -jar target/test-springboot-0.0.1-SNAPSHOT.jar'
  sh 'java -Xmx1g -noverify -Drun.mode=prod -jar target/test-springboot-0.0.1-SNAPSHOT.jar'
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

java -Xmx1G -Drun.mode=prod -jar lib/#{NAME}-#{VERSION}.jar
    eos
    File.write('dist/start.sh', start_content)
    
    sh 'chmod +x dist/start.sh'
  end
end

namespace :dev do
  desc 'init dev'
  task :init do
    sh 'gem install guard'
    sh 'gem install guard-livereload'
  end
end

namespace :tool do
  desc "tool javaagent"
  task :javaagent do 
    path = "~/.m2/repository/org/springframework/springloaded/1.2.3.RELEASE/springloaded-1.2.3.RELEASE.jar"
    puts "-javaagent:#{File.expand_path(path)} -noverify"
  end
end

