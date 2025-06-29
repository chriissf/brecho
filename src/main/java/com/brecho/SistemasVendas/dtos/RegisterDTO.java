package com.brecho.SistemasVendas.dtos;

import com.brecho.SistemasVendas.entities.UserRole;

public record RegisterDTO (String login, String password, UserRole role){


}
