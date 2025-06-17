package it.epicode.blogapp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.epicode.blogapp.exeption.NotFoundException;
import it.epicode.blogapp.model.User;
import it.epicode.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTool {
    @Autowired
    private UserService userService;

    @Value("${jwt.duration}")
    private long durata;

    @Value("${jwt.secret}")
    private String chiaveSegreta;

    public String creatToken(User user){
       return Jwts.builder().issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+durata)).
                subject(user.getId()+"").signWith(Keys.hmacShaKeyFor(chiaveSegreta.getBytes())).
                compact();
    }

    // metodo per la verifica della validita del token
    public void validateToken(String token){
        Jwts.parser().verifyWith(Keys.hmacShaKeyFor(chiaveSegreta.getBytes()))
                .build().parse(token);
    }

    public User  getUserFromToken(String token) throws NotFoundException {
        // recuperare l'id dell'utente dal token
      int id = Integer.parseInt(Jwts.parser().verifyWith(Keys.hmacShaKeyFor(chiaveSegreta.getBytes())).
                build().parseSignedClaims(token).getPayload().getSubject());
      return userService.getUser(id);
    }
}
