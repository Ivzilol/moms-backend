package bg.mck.authenticationservice.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Service
public class JwtUtil {


    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expiration}")
    private Long expirationTime;

    private Key key;

    public JwtUtil() {
    }

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }

    public String generateToken(String id,String email, Set<String> roles) {
        Map<String, String> claims = Map.of(
                "id", id,
                "email", email,
                "roles", String.join(",", roles),
                "expiration", String.valueOf(expirationTime)
        );

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(claims.get("email"))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }

    public String getRoles(String token) {
        return getClaims(token).get("roles", String.class);
    }
}
