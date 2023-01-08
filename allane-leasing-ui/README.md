# AllaneLeasingUi

## Technologies used:
* Angular
* Angular Material
* OpenApi Generator
* Docker
* Tilt - https://tilt.dev/

## Run (Recommended)

Run all apps (front-end and back-end) using the tilt file from root directory.

## Run

Start up AllaneLeasingUi using Tilt. Tilt uses the Dockerfile-local to build the image of AllaneLeasingUi app and then uses that image to run in docker container.

```
tilt up
```
Stop the app

```
tilt down
```

## Tasks achieved
* Implemented UIs:
  * All customers
  * Add new customer
* Consumed the APIs:
  * [POST] /customer
  * [GET] /customers
* Integrated OpenApi generator to consume the API specs
* Integrated and used Angular Material