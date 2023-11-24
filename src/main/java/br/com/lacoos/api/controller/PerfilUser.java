package br.com.lacoos.api.controller;

import br.com.lacoos.model.User;
import br.com.lacoos.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/perfil")
@AllArgsConstructor
@Slf4j
public class PerfilUser {

    private final UserService userService;


}