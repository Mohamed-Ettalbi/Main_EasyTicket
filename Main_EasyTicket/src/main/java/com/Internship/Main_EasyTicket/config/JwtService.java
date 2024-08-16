package com.Internship.Main_EasyTicket.config;
import io.jsonwebtoken.JwtParserBuilder;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY="55454e424a49352b394850384d4761346e2b43425a795170704159694c7433313657556a32587272414f4f71535332586f324d4b6b79684f55494a62443149466a6c787175647970424a793874474d713032324d306f6d71517a2f303347396d344f7270634c6a4f6167572b73616c33726373626c4a2f384e6a4d376e703069446a75316f4d7937384a7a4c53674d7550746c514e6e5065787176624e457a5a7a666c634e2f352b485466324b45517653424a7874596c374365354d4d5257485a79535561515a77743039722f646f693371445565576f4977506e526338754c504f613875334c6f634557752b71792f37575256624e453334355755323648346f79714a73574b72707a364a3551483069674c50675869766e563541746e657768795372684d4d6d5a45646b4265652b345370335963744e42652b42544c38443147414e54437573384c576d6e79514a496845555a6e2f785332447541424f4d4f31324e4f5777324b33303d";

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
