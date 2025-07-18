package Usuario;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    // Construtores (padrão e parametrizado) podem ser adicionados, se desejar

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorne uma lista vazia ou implemente roles/permissões se necessário
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Ajuste conforme regra do negócio
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Ajuste conforme regra do negócio
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Ajuste conforme regra do negócio
    }

    @Override
    public boolean isEnabled() {
        return true; // Ajuste conforme regra do negócio
    }

    // Getters e setters caso queira usar, mas com JPA + Spring Security não são obrigatórios no record style
    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}


