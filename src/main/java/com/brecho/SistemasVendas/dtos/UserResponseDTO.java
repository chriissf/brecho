package com.brecho.SistemasVendas.dtos;

import com.brecho.SistemasVendas.entities.UserRole;
import lombok.Data;

@Data
public class UserResponseDTO {
    private String id;
    private String login;
    private UserRole role;

    public UserResponseDTO(String id, String login, UserRole role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }
}
