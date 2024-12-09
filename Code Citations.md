# Code Citations

## License: unknown
https://github.com/JihadKhader/Order-Managment/tree/20eb2a6e74b92e29150e74b4d0efacc8ea862164/OrderManagment/src/main/java/com/jihad/project/OrderManagment/securityconfig/JwtService.java

```
/**
     * Extracts the username from the JWT token.
     *
     * @param token the JWT token
     * @return the username extracted from the token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     *
```


## License: unknown
https://github.com/pavlovicbojan/example-spring-security/tree/f773245cf324a2d6ded3550a5432bccd2e437ca2/src/main/java/com/code9/springsecurity/security/JwtService.java

```
the claim
     * @param <T> the type of the claim
     * @return the extracted claim
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims
```

