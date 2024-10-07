package med.voll.api.medico.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.domain.dto.DadosAtualizaMedico;
import med.voll.api.medico.domain.dto.DadosCadastroMedico;
import med.voll.api.medico.domain.dto.DadosDetalhamentoMedico;
import med.voll.api.medico.domain.dto.DadosListagemMedico;
import med.voll.api.medico.domain.entity.Medico;
import med.voll.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@Valid @RequestBody DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        Medico medico = this.repository.save(new Medico(dados));

        URI uri = uriBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping("list")
    public ResponseEntity<List<DadosListagemMedico>> listar() {
        List<DadosListagemMedico> list = this.repository.findAll()
                .stream()
//                .map(m -> new DadosListagemMedico(m.getId(), m.getNome(), m.getEmail(), m.getCrm(), m.getEspecialidade()))
                .map(DadosListagemMedico::new)
                .toList();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> paginacao(
            @PageableDefault(size = 10, page = 0, sort = {"nome"})
            Pageable pageable
    ) {
        Page<DadosListagemMedico> page = this.repository.findAllByAtivoTrue(pageable)
                .map(DadosListagemMedico::new);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosDetalhamentoMedico> detalhar(@PathVariable Long id) {
        Medico medico = this.repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DadosDetalhamentoMedico(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> atualizaMedico(@Valid @RequestBody DadosAtualizaMedico dados) {
        Medico medico = this.repository.getReferenceById(dados.id());
        medico.atualizaInformacoes(dados);
        return ResponseEntity.ok().body(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Medico medico = this.repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

}
