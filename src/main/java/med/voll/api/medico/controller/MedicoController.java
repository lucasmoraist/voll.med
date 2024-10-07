package med.voll.api.medico.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.domain.dto.DadosAtualizaMedico;
import med.voll.api.medico.domain.dto.DadosCadastroMedico;
import med.voll.api.medico.domain.dto.DadosListagemMedico;
import med.voll.api.medico.domain.entity.Medico;
import med.voll.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    public Medico cadastrar(@Valid @RequestBody DadosCadastroMedico dados) {
        return this.repository.save(new Medico(dados));
    }

    @GetMapping("list")
    public List<DadosListagemMedico> listar() {
        return this.repository.findAll()
                .stream()
//                .map(m -> new DadosListagemMedico(m.getId(), m.getNome(), m.getEmail(), m.getCrm(), m.getEspecialidade()))
                .map(DadosListagemMedico::new)
                .toList();
    }

    @GetMapping
    public Page<DadosListagemMedico> paginacao(
            @PageableDefault(size = 10, page = 0, sort = {"nome"})
            Pageable pageable
    ) {
        return this.repository.findAllByAtivoTrue(pageable)
                .map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizaMedico(@Valid @RequestBody DadosAtualizaMedico dados) {
        var medico = this.repository.getReferenceById(dados.id());
        medico.atualizaInformacoes(dados);
    }

    @DeleteMapping("{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var medico = this.repository.getReferenceById(id);
        medico.excluir();
    }

}
