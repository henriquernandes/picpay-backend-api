package com.picpay.api.dtos;

import com.picpay.api.domains.user.UserType;

import java.math.BigDecimal;

public record UserDTO(
        String firstName,
        String lastName,
        String email,
        String cpfCnpj,
        String password,
        BigDecimal balance,
        UserType type) {
}
