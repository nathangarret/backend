package br.com.lacoos.api.controller;

import br.com.lacoos.api.request.LoginRequest;
import br.com.lacoos.api.request.UserRequest;
import br.com.lacoos.api.response.TokenResponse;
import br.com.lacoos.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody @Valid UserRequest userRequest) {
        return userService.signUp(userRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @GetMapping
    public String Home(){
        return "Olá mundo!";
    }
}
