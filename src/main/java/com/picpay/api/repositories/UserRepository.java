package com.picpay.api.repositories;

import com.picpay.api.domains.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByCpfCnpj(String cpfCnpj);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Long id);


}
