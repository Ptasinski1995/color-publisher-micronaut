# Publish Color App
This small service is responsible for publishing some RGB colors to a Rabbit Queue.
RGB Colors are first filtered out to those that should be published and accordingly mapped to their names, 
later send to queueand eventually read from the queue and logged.

## What you will need
First you will need to:

- download and unzip the sources
- clone this Git repository with *git clone*

## Building Application

To build an application simply use:

> ./gradlew build

## Testing Application
To run the tests use: 

> ./gradlew test

After all the tests have been executed you can open up the test build report with:

> open build/reports/tests/test/index.html

Additionally there are basic benchmark test implemented using JMH. To run them use:

> gradle jmh

## Running Application

In order to run this application you will be needing RabbitMQ running with default configuration. For that try:

 > docker run --rm -it \
           -p 5672:5672 \
           -p 15672:15672 \
           rabbitmq:3.7.11-management

Once RabbitMQ is up and running there is nothing stopping you from running:

> ./gradlew run

Application will start on port 8080, to confirm its behaviour try:

> curl -i  -H "Content-Type: application/json" -H "Accept: application/json" -w "\n" -d '[{"color": "255,0,0", "publish":true}]' localhost:8080/publish   

You can add as many colors to the list as you want. However just this one call is enough to confirm that message has been send to the queue and read from it. 
Inside of a httpRequests folder you can also find ready to use IntelliJ Http Request.
