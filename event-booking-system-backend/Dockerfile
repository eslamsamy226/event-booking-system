FROM openjdk:17-jdk-slim

# Install dependencies for live reloading
RUN apt-get update && apt-get install -y maven

# Set the working directory
WORKDIR /app

# Copy the pom.xml and install dependencies (cache layer)
COPY pom.xml /app/
#RUN mvn dependency:go-offline

# Copy the source code
COPY src /app/src
# Set environment variables for database credentials
ENV SPRING_DATASOURCE_URL=${DB_URL}
ENV SPRING_DATASOURCE_USERNAME=${DB_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD=${DB_PASSWORD}

CMD ["mvn", "spring-boot:run"]

# Expose the application's port
EXPOSE 8080
