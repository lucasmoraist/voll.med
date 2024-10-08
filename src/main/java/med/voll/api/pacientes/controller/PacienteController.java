package med.voll.api.pacientes.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.pacientes.domain.dto.DadosAtualizaPaciente;
import med.voll.api.pacientes.domain.dto.DadosCadastroPaciente;
import med.voll.api.pacientes.domain.dto.DadosListagemPaciente;
import med.voll.api.pacientes.domain.entity.Paciente;
import med.voll.api.pacientes.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public Paciente save(@Valid @RequestBody DadosCadastroPaciente dados) {
        return this.repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listar(
            @PageableDefault(sort = {"nome"}, direction = Sort.Direction.ASC, size = 10)
            Pageable pageable) {
        return this.repository.findAll(pageable)
                .map(DadosListagemPaciente::new);
    }

    @PutMapping
    @Transactional
    public void atualizaPaciente(@Valid @RequestBody DadosAtualizaPaciente dados) {
        var paciente = this.repository.getReferenceById(dados.id());
        paciente.atualizaInformacoes(dados);
    }

    @DeleteMapping("{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var paciente = this.repository.getReferenceById(id);
        paciente.excluir();
    }

}
