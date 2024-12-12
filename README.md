# famapi
A RESTful API for managing family trees.

## Prerequisites
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Java Development Kit (JDK) 21](https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21.0.5%2B11/OpenJDK21U-jdk_x64_windows_hotspot_21.0.5_11.zip)
- (optional) [Maven 3.6+](https://maven.apache.org/download.cgi)

## Run
**Recommended:** To use the Maven Wrapper and the provided JDK, run the following command to start the server:

- **Windows**
    ```bash
    ./mvnw.cmd spring-boot:run
    ```
- **Linux**
    ```bash
    ./mvnw spring-boot:run
    ```

You can also run the following command to forces the use of the Maven Central Repository
```bash
    ./mvnw.cmd -s .\.mvn\wrapper\settings.xml spring-boot:run
```
*Note:*  to The flag `-s .\.mvn\wrapper\settings.xml` forces the use of the Maven Central Repository for downloading dependencies, overriding any settings in the `.m2/settings.xml` file.

The server will start on [localhost:8080](http://localhost:8080).


## API Documentation
### Swagger UI 
swagger is available [http://localhost:8080/swagger-ui/index.html](swagger-ui)