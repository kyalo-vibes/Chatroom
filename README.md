Chatroom Application
-----

## Introduction

Chatroom Application is a SpringBoot app created to demonstrate knowledge of client-server architecture and websockets. WebSocket is a communication protocol that makes it possible to establish a two-way communication channel between a
server and a client.

## Functionality
Message model is the message payload that will be exchanged between the client and the server. Message class covers the following actions:
1. ENTER
2. CHAT
3. LEAVE

## Tech Stack (Dependencies)

### 1. Setting up
Our tech stack will include the following:
* **spring-boot-starter-thymeleaf** - A Spring Boot starter for Thymeleaf templating engine.
* **spring-boot-starter-web** - A Spring Boot starter for building web, including RESTful, applications using Spring MVC.
* **spring-boot-starter-websocket** - A Spring Boot starter for building WebSocket applications using Spring WebSockets.
* **spring-boot-starter-test** - A Spring Boot starter for writing unit and integration tests with Spring Test and JUnit.
* **webjars-locator-core** - A core library for locating WebJar assets on the classpath.
* **sockjs-client** - A JavaScript client library for SockJS, a WebSocket emulation library.
* **stomp-websocket** - A JavaScript client library for the STOMP protocol over WebSocket.
* **bootstrap** - A popular HTML, CSS, and JS framework for developing responsive, mobile-first projects on the web.
* **mdui** - A Material Design UI library for developing responsive and mobile-first websites.
* **jquery** - A fast, small, and feature-rich JavaScript library for developing web pages.
* **junit** - A unit testing framework for Java programming language.
* **fastjson** - A fast JSON parser and generator for Java.
* **selenium-server** - A standalone server that allows you to create WebDriver tests for web applications.


## Main Files: Project Structure

  ```sh
  ├── README.md
  ├── pom.xml 
  ├── ChatRoomApplication
  ├── chat
  │   ├── WebSocketConfig
  │   ├── WebSocketChatServer
  │   └── Message
  ├── static
  │   └── login-bg.jpg
  └── templates
      ├── chat
      └── login
  ```

Overall:
The file structure represents a project directory for a Chat Room application. The key components of the structure are:

* `README.md` -- a file that contains information about the project and how to use it.
* `pom.xml` -- a file that manages the project's dependencies, build settings, and other configurations in Maven.
* `ChatRoomApplication` -- a folder that contains the main application class and acts as the entry point of the application.
* `chat` -- a folder that contains the classes for WebSocket configuration, handling chat messages, and WebSocket server implementation.
* `static` -- a folder that contains static files used in the application such as images, CSS, and JavaScript files.
* `templates` -- a folder that contains the HTML templates used to render the web pages. It includes two subfolders: chat and login, which contain the templates for the chat and login pages respectively.


## Development Setup
1. **Download the project starter code locally**
```
git clone https://github.com/kyalo-vibes/Chatroom
```

2. **Run the development server:**

```
mvn build; mvn spring-boot:run
```
3. **Verify on the Browser**<br>
   Navigate to project homepage [http://127.0.0.1:8080/](http://127.0.0.1:8080/) or [http://localhost:8080](http://localhost:8080) 

