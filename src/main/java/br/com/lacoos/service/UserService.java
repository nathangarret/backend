package br.com.lacoos.service;

import br.com.lacoos.api.request.LoginRequest;
import br.com.lacoos.api.request.UserRequest;
import br.com.lacoos.api.response.TokenResponse;
import br.com.lacoos.infra.exceptions.InvalidParamsException;
import br.com.lacoos.model.User;
import br.com.lacoos.repository.UserRepository;
import br.com.lacoos.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public ResponseEntity<Void> signUp(UserRequest userRequest){
        log.info("Save user: {}", userRequest);
        if (userRepository.existsByEmailOrCpf(userRequest.getEmail(), userRequest.getCpf())) {
            log.error("E-mail ou CPF já existe");
            throw new InvalidParamsException("E-mail ou CPF já existe");
        }
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        user.setBirthDate(DateUtils.parseLocalDate(userRequest.getBirthDate()));
        user.setPassword(encoder.encode(userRequest.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<TokenResponse> login(LoginRequest loginRequest) {
        log.info("Login user: {}", loginRequest);
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication auth = manager.authenticate(userToken);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new TokenResponse(token));
    }

    public User userForId(Long id){
    }

}
