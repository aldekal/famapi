# famapi
A RESTful API for managing family trees.

## Prerequisites
- (optional) [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- (optional) [Java Development Kit (JDK) 21](https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21.0.5%2B11/OpenJDK21U-jdk_x64_windows_hotspot_21.0.5_11.zip)
- (optional) [Maven 3.6+](https://maven.apache.org/download.cgi)

*Note:* __No prerequisites are required__, as the project utilizes the Maven Wrapper to automatically download the necessary dependencies and provides JDK 21 in the `.mvn` folder.

The commands below will leverage the Maven Wrapper along with the specified Java version to download the required dependencies and run the server. You can also use your own JDK and Maven if you prefer.

## Run
**Recommended:** To use the Maven Wrapper and the provided JDK, run the following command to start the server:

- **Windows**
    ```bash
    ./mvnw.cmd -s .\.mvn\wrapper\settings.xml spring-boot:run
    ```
- **Linux**
    ```bash
    ./mvnw -s ./.mvn/wrapper/settings.xml spring-boot:run
    ```

*Note:* The flag `-s .\.mvn\wrapper\settings.xml` forces the use of the Maven Central Repository for downloading dependencies, overriding any settings in the `.m2/settings.xml` file.

The server will start on [localhost:8080](http://localhost:8080).

If you prefer to use your own Maven and JDK, run the following command to start the server:

- **Windows**
    ```bash
    mvn spring-boot:run
    ```
- **Linux**
    ```bash
    mvn spring-boot:run
    ```

The server will also start on [localhost:8080](http://localhost:8080).


## API Documentation
### Swagger UI 
swagger is available [http://localhost:8080/swagger-ui/index.html](swagger-ui)