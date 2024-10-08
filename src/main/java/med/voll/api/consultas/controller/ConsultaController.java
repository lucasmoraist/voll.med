package med.voll.api.consultas.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.consultas.domain.dto.DadosAgendamentoConsulta;
import med.voll.api.consultas.domain.dto.DadosDetalhamentoConsulta;
import med.voll.api.consultas.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consulta")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agendar (@Valid @RequestBody DadosAgendamentoConsulta dados) {
        var response = this.service.agendarConsulta(dados);
        return ResponseEntity.ok().body(response);
    }

}
