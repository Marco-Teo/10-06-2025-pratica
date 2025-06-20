package it.epicode.blogapp.service;

import it.epicode.blogapp.dto.LogInDto;
import it.epicode.blogapp.exeption.NotFoundException;
import it.epicode.blogapp.model.User;
import it.epicode.blogapp.repository.UserRepository;
import it.epicode.blogapp.security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(LogInDto logInDto) throws NotFoundException {
      User user = userRepository.findByUsername(logInDto.getUsername()).
                orElseThrow(()->new NotFoundException("Utente con questo username/password non trovato"));

      if (passwordEncoder.matches(logInDto.getPassword(), user.getPassword())) {
          return jwtTool.creatToken(user);
      }
      else {
          throw new NotFoundException("Utente con questo username/password non trovato");
      }
    }
}
