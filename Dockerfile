# Start with a base image containing Java runtime
FROM openjdk:8-jre-alpine

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8443 available to the world outside this container
EXPOSE 8443

# The application's jar file
ARG JAR_FILE

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","/app.jar"]