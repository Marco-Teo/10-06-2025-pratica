package it.epicode.blogapp.security;

import it.epicode.blogapp.exeption.UnAuthorizedExeption;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTool jwtTool;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (authorization ==  null || !authorization.startsWith("Bearer ")){
            throw new UnAuthorizedExeption("Token non presente, non sei autorizzato ad usare il servizio richiesto");
        }
        else {
            // estraggo il token dalla stringa autorization che contiene anche la parola beare  prima del toke e per questo prendo solo
            //la parte della stringa dopo lo spazio che comincia dal carattere 7 beare5+spazzio6 parto dal 7
            String token = authorization.substring(7);
          // verifico che il token sia valido
            jwtTool.validateToken(token);
            filterChain.doFilter(request,response);
        }

    }
    // questo metodo evita che gli end point di registrazione e lgo in possano richeidere il token
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
