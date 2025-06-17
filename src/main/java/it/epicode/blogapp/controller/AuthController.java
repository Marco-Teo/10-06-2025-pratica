package it.epicode.blogapp.controller;

import it.epicode.blogapp.dto.LogInDto;
import it.epicode.blogapp.dto.UserDto;
import it.epicode.blogapp.exeption.NotFoundException;
import it.epicode.blogapp.model.User;
import it.epicode.blogapp.service.AuthService;
import it.epicode.blogapp.service.AutoreService;
import it.epicode.blogapp.service.UserService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/auth/register")
    public User register(@RequestBody @Validated UserDto userDto, BindingResult bindingResult)throws ValidationException{
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage()).
                    reduce("",( s,e) ->s+e));
        }
        return userService.saveUser(userDto);
    }

    // LOGIN - autentica utente
    @GetMapping("/auth/login")
    public String login(@RequestBody @Validated LogInDto logInDto, BindingResult bindingResult) throws ValidationException, NotFoundException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (s, e) -> s + e));
        }
        return authService.login(logInDto);
    }
    
}

