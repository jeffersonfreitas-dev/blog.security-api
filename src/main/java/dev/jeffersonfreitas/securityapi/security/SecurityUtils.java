package dev.jeffersonfreitas.securityapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jeffersonfreitas.securityapi.exceptions.NotAuthorizedException;
import dev.jeffersonfreitas.securityapi.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;


public class SecurityUtils {

    private static final String ISSUR = "jeffersonfreitas-dev";
    private static final String TOKEN_KEY = "abcd0123efgh0456ijkl0789mnop0000";
    private static final long EXPIRATION = 60*1000; // 1 minute

    public static String encode(User user) {
        var expiration = new Date(System.currentTimeMillis() + EXPIRATION);
        TokenResponse tokenResponse = TokenResponse.create(createTokenJWT(user, expiration), expiration);
        try {
            return new ObjectMapper().writeValueAsString(tokenResponse);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error creating token response");
        }
    }

    public static String verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_KEY.getBytes());

        try{
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer(ISSUR)
                    .acceptExpiresAt(5)
                    .build();
            return jwtVerifier.verify(token).getSubject();
        }catch (JWTVerificationException e){
            throw new NotAuthorizedException("Token error occurred");
        }
    }

    public static boolean isValidPassword(String password, String savedPassword) {
        if(password == null || password.trim().isBlank()) return false;
        return new BCryptPasswordEncoder().matches(password, savedPassword);
    }

    private static String createTokenJWT(User user, Date expiration) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_KEY.getBytes());
            return JWT.create()
                    .withSubject(user.getUsername())
                    .withIssuer(ISSUR)
                    .withExpiresAt(expiration)
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new IllegalArgumentException("Error creating token");
        }
    }
}
