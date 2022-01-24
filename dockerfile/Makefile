APP_NAME = truck-api-framework

.PHONY: build-image

build-image:
    cd ..; docker build -f dockerfile/Dockerfile --no-cache --force-rm=true -t ${APP_NAME} \
        --build-arg JAR_FILE=${JAR_FILE} .
