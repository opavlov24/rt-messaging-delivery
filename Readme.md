# RealTime-Messaging-Delivery

## Description

The project illustrates different concepts such as REST, messaging and websockets. 

#### What's it about?

The service is responsible for persisting dummy objects and notifying clients about it using websockets.

## API

1. Create a new dummy object.

    ```
        Request: POST /dummies
        Body: { "message": "Some message" }
        
        Response: 201 Accepted
        Response: 409 Bad request
    ```
2. Get all dummy objects (paged).

    ```
        Request: GET /dummies
        
        Response: 200 OK
        Response body:
        {
            "_embedded":{ 
                "dummies":[
                    {"id":"d216acf8-4f0d-48bc-ae86-6a3a14e4e81e","message":"Some message"}
                ]
            },"
            _links":{
                "self": {
                    "href":"http://localhost/dummies?page=0&size=10"
                    }
                },
                "page": {
                    "size":10,
                    "totalElements": 1,
                    "totalPages":1,"number":0
                }
        }
    ```

## How to run

Firstly,you need to go to the project's directory and run the following command:
    
    docker-compose -f deployment.yml up
    
and wait. When the application is up and running, open http://localhost in your browser.

Secondly, you need to open a rest client and make the first request (create a dummy object), after that you will 
see the message in your browser.

## Requirements

1. Internet connection
2. Docker Engine 1.13.0+ and Docker compose 1.11.+

 
## Used technologies

1. Java 8
2. Gradle 4.8
3. Spring Boot 1.5.13 (web, websockets, data, amqp, hateos)
4. Mongo 3.6 and Rabbit 3

## What should be improved

1. Use json format for the queues.
2. Extract the websocket topic path into the configuration.
3. Configure dead letter / retry policy for the queue's listeners.
4. Add an addition functional test to test websockets.