package med.voll.api.medico.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.domain.entity.Endereco;
import med.voll.api.medico.domain.dto.DadosAtualizaMedico;
import med.voll.api.medico.domain.dto.DadosCadastroMedico;
import med.voll.api.medico.domain.enums.Especialidade;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Medico")
@Table(name = "t_voll_medico")
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    /**
     * A anotação @Embedded é utilizada para mapear uma entidade que é parte de outra entidade.
     * Neste caso, a entidade Endereco é parte da entidade Medico, fazendo com que os atributos
     * da entidade Endereco sejam mapeados na tabela t_voll_medico.
     * Na classe Endereco, a anotação @Embeddable é utilizada para indicar que a classe é uma
     * entidade que será mapeada como parte de outra entidade.
     */
    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizaInformacoes(DadosAtualizaMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizaInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
