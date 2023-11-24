package br.com.lacoos.repository;

import br.com.lacoos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailOrCpf(String email, String cpf);

    UserDetails findByEmail(String email);
}