package than.MK.weblaptop.backend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import than.MK.weblaptop.backend.entity.Rights;
import than.MK.weblaptop.backend.entity.User;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtService {
    public static final String SERECT = "6565474765787537853727382B47465876756V476457658678U78757357239M656";
    @Autowired
    private UserServiceInterface userServiceInterface;
    // Create JWT base on username
    public String generateToken(String userName){
        Map<String, Object> claims = new HashMap<>();
        User user = userServiceInterface.findByUserName(userName);

        boolean isAdmin = false;
        boolean isStaff = false;
        boolean isUser = false;
        boolean isAccount = false;
        if(user != null && user.getListRight().size()>0){
            List<Rights> list = user.getListRight();
            for(Rights r: list){
                if(r.getRightName().equals("Admin")){
                    isAdmin = true;
                }
                if(r.getRightName().equals("Staff")){
                    isStaff = true;
                }
                if(r.getRightName().equals("User")){
                    isUser = true;
                }
                if(r.getRightName().equals("Account")){
                    isAccount = true;
                }
            }
        }
        claims.put("isAdmin", isAdmin);
        claims.put("isStaff", isStaff);
        claims.put("isUser", isUser);
        claims.put("isAccount", isAccount);

        return  createToken(claims, userName);
    }

    // Create JWT with selected claim
    private  String createToken(Map<String, Object> claims,String userName){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 30*60*1000))
                .signWith(SignatureAlgorithm.HS256, getSignKey())
                .compact();
    }

    // Take serect key
    private Key getSignKey() { // Key java.security
        byte[] keyBytes = Decoders.BASE64.decode(SERECT);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    // Take information
    private Claims extractAllClaims(String token){
        // add .build()
        return Jwts.parser().setSigningKey(getSignKey()).build().parseSignedClaims(token).getPayload();
    }

    // Take information for one claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction){
        final Claims claims = extractAllClaims(token);
        return  claimsTFunction.apply(claims);
    }

    //Checking expire time from JWT
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    //get username from JWT
    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //Checking expired JWT
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //Checking for validity
    public Boolean validateToken(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        System.out.println(userName);
        return (userName.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }







}
