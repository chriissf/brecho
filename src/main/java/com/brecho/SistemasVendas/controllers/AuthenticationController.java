package com.brecho.SistemasVendas.controllers;


import com.brecho.SistemasVendas.dtos.LoginResponseDto;
import com.brecho.SistemasVendas.dtos.RegisterDTO;
import com.brecho.SistemasVendas.dtos.authenticationDTO;
import com.brecho.SistemasVendas.entities.User;
import com.brecho.SistemasVendas.repositories.UserRepository;
import com.brecho.SistemasVendas.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AuthenticationController {

    @Autowired
    TokenService tokenService;


    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid authenticationDTO data){

        var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken ((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){

        if(this.repository.findByLogin(data.login()) != null) return  ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);
        return  ResponseEntity.ok().build();


    }

}
