#!/bin/sh

isDir() {
  cat <<EOF
------------------------
Not exists "$1" directory!
Correct path: c:/path/to/folder/
-------------------------
EOF
}

LOCAL_VOLUME_PATH=$1

if [[ ! -d $LOCAL_VOLUME_PATH ]]; then
  isDir ${LOCAL_VOLUME_PATH}
  exit 1
fi
PROJECT_NAME=cucc
DOCKER_APP_NAME=fifth-service
VERSION_TAG=1.0.0
IMAGE_NAME=${PROJECT_NAME}/${DOCKER_APP_NAME}:${VERSION_TAG}
CONTAINER_NAME=fifth-service-1
DOCKER_BUILD_PATH=.
docker rm $(docker stop $(docker ps -aq --filter name=${CONTAINER_NAME}))
docker system prune -f
echo "--------------------------------------------"
echo "------------- Create Image -----------------"
echo "--------------------------------------------"
docker build -t ${IMAGE_NAME} -f ./${DOCKER_BUILD_PATH}/Dockerfile ./${DOCKER_BUILD_PATH}/
echo "-------------------------------------------------"
echo "------------- Container Running -----------------"
echo "-------------------------------------------------"
#docker run --rm -it --name ${CONTAINER_NAME} -v ${LOCAL_VOLUME_PATH}:/app/properties -e ENVIRONMENT=dev -p 8081:8081 ${IMAGE_NAME}
docker run --rm -it --name ${CONTAINER_NAME} --env PORT=9095 -p 9095:9095 ${IMAGE_NAME}
