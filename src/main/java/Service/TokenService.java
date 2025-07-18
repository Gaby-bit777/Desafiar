package com.seuprojeto.forum.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.seuprojeto.forum.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration; // valor em horas (ex: "2" para 2h)

    public String gerarToken(Usuario usuario) {
        Algorithm algoritmo = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("forumhub-api")
                .withSubject(usuario.getLogin())
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo);
    }

    private Instant dataExpiracao() {
        long expHoras = Long.parseLong(expiration);
        return Instant.now().plus(Duration.ofHours(expHoras));
    }

    public String getSubject(String tokenJWT) {
        return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("forumhub-api")
                .build()
                .verify(tokenJWT)
                .getSubject();
    }
}



