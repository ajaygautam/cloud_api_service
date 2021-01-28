FROM amazoncorretto:15

ARG JAR_FILE=build/libs/cloud_api_service*.jar

COPY ${JAR_FILE} /app/cloud_api_service.jar

ENTRYPOINT ["java","-jar","/app/cloud_api_service.jar"]
