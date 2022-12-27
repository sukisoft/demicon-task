# demicon-task

## api-connector

### prerequisites

- maven 3.6.x
- Java 11+

### compiling the app

navigate to the project root directory and run the following maven commands or import the project within an IDE and make use of the run-configurations that will
be pre-configured if you are using Intelli-J.

```shell
mvn clean install
```

then run the created jar-file with e.g.
```shell
java -jar ./target/api-connector-1.0.jar --randomuser-api.userSize=10
```

### settings

The connector can be configured by the 3 following settings:

#### randomuser-api.url

The URL of the randomuser-api, has to be set and be not null.

*default value*: https://randomuser.me/api

*command line usage*: --randomuser-api.url=*myurl*

#### randomuser-api.userSize

The amount of users to retrieve in one synchronization. A minimum of 1 and a maximum of 100 is applied.

*default value*: 25

*command line usage*: --randomuser-api.userSize=50

#### randomuser-api.period

The time-period in seconds in which a synchronization will be performed. A minimum of 10 seconds and a maximum of 1 hour is applied.

*default value*: 60

*command line usage*: --randomuser-api.period=100

## api-connector web-app

The web-app can be found at http://localhost:8080/view/home

## rest endpoints

This application exposes the following rest-endpoints. The Open-API specification can be found at http://localhost:8080/api/v1/swagger-ui/index.html

*api-root*: /api/v1/

### /api/v1

retrieve the Countries-Response in the form of

```json
{
    "countries": [
        {
            "name": "Australia",
            "users": [
                {
                    "gender": "male",
                    "name": "Luis Harrison",
                    "email": "luis.harrison@example.com"
                },
                {
                    "gender": "male",
                    "name": "Jon Horton",
                    "email": "jon.horton@example.com"
                }
            ]
        }
    ]
}
```

## /api/v1/countries

retrieve a list of all countries that are currently present

```json
[
    {
        "name": "Australia",
        "users": []
    },
    {
        "name": "Brazil",
        "users": []
    }
]
```