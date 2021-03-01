# Maven build
## Service and javadoc
```shell script
mvn -U clean install
```
### Skip javadoc
```shell script
mvn -U clean install -PskipJavaDoc
```
# Service running local
## with maven
```shell script
java -jar ./fifth-cucc-service/target/fifth-cucc-service-x.x.x-x.jar
```
## with docker build and container running
```shell script
./build-docker.sh ./
```
# Heroku usage
```shell script
heroku --help
```
## Create app with name
```shell script
heroku create <app_name>
heroku create fifth-cucc-service
# https://fifth-cucc-service.herokuapp.com/ | https://git.heroku.com/fifth-cucc-service.git
```
## Switch heroku stack
```shell script
heroku stack:set container --app <app_name>
heroku stack:set container --app fifth-cucc-service
```
## Heroku logging
```shell script
heroku logs --tail
heroku logs -p postgres -t
```
# Heroku Addons
```shell script
# default postgresql version: 12
heroku addons:create heroku-postgresql:hobby-dev
heroku addons:create heroku-postgresql:hobby-dev --version=13 --name=postgresql-fifth-cucc-db
heroku pg:info
```
