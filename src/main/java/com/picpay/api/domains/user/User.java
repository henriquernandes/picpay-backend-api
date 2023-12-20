package com.picpay.api.domains.user;

import com.picpay.api.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String cpfCnpj;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType type;

    public User (UserDTO userData) {
        this.firstName = userData.firstName();
        this.lastName = userData.lastName();
        this.email = userData.email();
        this.cpfCnpj = userData.cpfCnpj();
        this.password = userData.password();
        this.balance = userData.balance();
        this.type = userData.type();
    }

}