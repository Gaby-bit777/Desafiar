@Service
public class TokenService {

    private static final String CHAVE_SECRETA = "minha-chave-secreta";

    public String gerarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getLogin())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 dia
                .signWith(SignatureAlgorithm.HS256, CHAVE_SECRETA)
                .compact();
    }

    public String validarToken(String token) {
        return Jwts.parser()
                .setSigningKey(CHAVE_SECRETA)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

