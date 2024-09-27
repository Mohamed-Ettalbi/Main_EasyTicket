package com.Internship.Main_EasyTicket.config;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.JwtParserBuilder;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    public  String extractUsername(String token){

        return extractClaim(token,Claims::getSubject);
    }


    public <T> T extractClaim(String token, Function<Claims,T> claimResolver)
    {

        final Claims claims = extractClaims(token);
        return claimResolver.apply(claims);

    }

    public String generateToken(UserDetails userDetails){
       return generateToken( new HashMap<>(),userDetails);


    }
    public boolean isTokenValid(String token,UserDetails userDetails){

        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {

        return extractClaim(token,Claims::getExpiration);
    }


    public String generateToken(
            Map<String , Object> extraClaims
            , UserDetails userDetails
    ){
        // Add the role to the extra claims
        extraClaims.put("role", userDetails.getAuthorities().stream()
             .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.toList()));



        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();




    }


    private Claims extractClaims(String token){

         return Jwts
                 .parser()
                 .setSigningKey(getSignInKey())
                 .build()
                 .parseClaimsJws(token)
                 .getBody();


//        private Claims getAllClaimsFromToken(String token) {
//            return Jwts.parser()
//                    .verifyWith(getPublicSigningKey())
//                    .build()
//                    .parseSignedClaims(token)
//                    .getPayload();


    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
