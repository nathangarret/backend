package br.com.lacoos.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @NotBlank @Email
    private String email;

    @NotBlank
    private String password;
}
