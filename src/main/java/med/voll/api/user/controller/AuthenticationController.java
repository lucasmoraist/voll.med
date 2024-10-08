package med.voll.api.user.controller;

import jakarta.validation.Valid;
import med.voll.api.infra.security.TokenService;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.user.domain.dto.LoginDTO;
import med.voll.api.user.domain.entity.Usuario;
import med.voll.api.user.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService service;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("login")
    public ResponseEntity<DadosTokenJWT> login(@Valid @RequestBody LoginDTO dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var auth = this.authenticationManager.authenticate(authToken);

        var tokenJWT = this.service.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok().body(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(@Valid @RequestBody LoginDTO dto, UriComponentsBuilder uriBuilder) {
        Usuario usuario = Usuario.builder()
                .login(dto.login())
                .senha(this.passwordEncoder.encode(dto.senha()))
                .build();

        var uri = uriBuilder
                .path("/auth/register")
                .build()
                .toUri();

        this.repository.save(usuario);

        return ResponseEntity.created(uri).build();
    }

}
