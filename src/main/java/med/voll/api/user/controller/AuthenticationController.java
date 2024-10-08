package med.voll.api.user.controller;

import jakarta.validation.Valid;
import med.voll.api.user.domain.dto.LoginDTO;
import med.voll.api.user.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;

    @PostMapping("login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginDTO dto) {
        var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var auth = this.authenticationManager.authenticate(token);

        return ResponseEntity.ok().build();
    }

}
