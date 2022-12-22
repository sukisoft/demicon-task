# demicon-task

## api-connector

### compiling the app

navigate to the project root directory and run the following maven commands or import the project within an IDE and make us of the run-configurations that will
be pre-configured if you are using Intelli-J.

```shell
mvn clean install
```

```shell
mvn sping-boot:run
```

### settings

the connector can be configured by the 3 following settings:

#### url

the URL of the randomuser-api, has to be set and be not null.

*default value*: https://randomuser.me/api

#### userSize

the amount of users to retrieve in one synchronization.

*default value*: 25
*min value*: 1
*max value*: 100

#### period

the time-period in seconds in which a synchronization will be performed.

*default value*: 60
*min value*: 10
*max value*: 3600

## rest endpoints

this application exposes the following rest-endpoints.

*api-root*: api

### /

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

## /countries

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