# allane-leasing

## Technologies used:
* Spring Boot
* Gradle
* Project Lombok
* OpenApi Generator
* MariaDB
* Flyway
* Docker
* Kubernetes
* Tilt - https://tilt.dev/

## Run

Start up allane leasing service and mariadb using Tilt. Tilt uses the Dockerfile-local to build the image of allane-leasing app and then uses kubernetes files for starting allane leasing app and mariaDB in containers.

```
tilt up
```
Stop the apps

```
tilt down
```

## Tasks achieved

Implemented the APIs:
* [POST] /customer
* [GET] /customers

Unit and Integration tests:
* Integration tests need a running database and in current state when th application is build by tilt, then tilt first boots up the DB and then build the app with gradle test task.
* Few unit tests are implemented but there is need for more tests

* Allane leasing app and MariaDB running in containers
* Flyway integrated
* OpenApi generator integrated