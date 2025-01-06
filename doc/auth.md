# Authentication

The application uses [Spring Security JDBC Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/jdbc.html)
Spring Security to secure the endpoints. The application uses basic authentication and JDBC to authenticate and authorize users. The application uses the following tables to store the user and role information:

```sql	
-- Create the users table
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username citext NOT NULL UNIQUE, -- Case-insensitive primary key
    password citext NOT NULL,            -- Case-insensitive password (if needed)
    enabled BOOLEAN NOT NULL             -- Boolean data type for enabled
);

-- Create the authorities table
CREATE TABLE authorities (
    username citext NOT NULL,            -- Case-insensitive username
    authority citext NOT NULL,           -- Case-insensitive authority
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);
```

Configuration for the authentication is done in the `SecurityConfig` class.
