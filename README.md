# Repository Explorer
This is a REST web service for exploring code repositories. For now, it is compatible only with GitHub and capable of using only public repositories.

## Installation
This project requires Java 11 and Maven.

To compile java into jar:

```mvn package```

To run the server the following command might be used:

```java -jar target/explorer-0.0.1.jar```

## Usage
The server is listening on port ```8000``` by default. You can explore the available endpoints with the swagger:
```http://localhost:8000/swagger-ui.html```

## Todo
The application in the current state is very limited, and it contains a lot of redundant code.
However, the main goal behind this code is to show some architectural concepts and ensure that adding new features,
for instance handling another repository host, is fast and easy. To improve the scalability - asynchronous request
 handling can be implemented as well as caching mechanisms.