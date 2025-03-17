// package com.ticketing.user_service.security;

// import java.util.Date;
// import java.util.function.Function;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import io.jsonwebtoken.*;

// @Component
// public class JwtUtil {

//     @Value("${security.secret-key}")
//     private String secretKeyString;

//     public String generateToken(String username) {
//         return Jwts.builder()
//                 .setSubject(username)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
//                 .signWith(SignatureAlgorithm.HS256, secretKeyString)
//                 .compact();
//     }

//     public String extractUsername(String token) {
//         return extractClaim(token, Claims::getSubject);
//     }

//     public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//         final Claims claims = Jwts.parser()
//                 .setSigningKey(secretKeyString)
//                 .parseClaimsJws(token)
//                 .getBody();
//         return claimsResolver.apply(claims);
//     }

//     public boolean validateToken(String token, String username) {
//         final String extractedUsername = extractUsername(token);
//         return extractedUsername.equals(username) && !isTokenExpired(token);
//     }

//     private boolean isTokenExpired(String token) {
//         return extractClaim(token, Claims::getExpiration).before(new Date());
//     }

//     public Date extractTokenExpirationDate(String token) {
//         return extractClaim(token, Claims::getExpiration);
//     }

//     public String generateRefreshToken(String username) {
//         return Jwts.builder()
//                 .setSubject(username)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
//                 .signWith(SignatureAlgorithm.HS256, secretKeyString)
//                 .compact();
//     }

//     public <T> T extractRefreshClaim(String token, Function<Claims, T> claimsResolver) {
//         final Claims claims = Jwts.parser()
//                 .setSigningKey(secretKeyString)
//                 .parseClaimsJws(token)
//                 .getBody();
//         return claimsResolver.apply(claims);
//     }

//     public Date extractRefreshTokenExpirationDate(String token) {
//         return extractRefreshClaim(token, Claims::getExpiration);
//     }
// }
