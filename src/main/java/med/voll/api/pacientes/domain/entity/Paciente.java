package med.voll.api.pacientes.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.entity.Endereco;
import med.voll.api.pacientes.domain.dto.DadosAtualizaPaciente;
import med.voll.api.pacientes.domain.dto.DadosCadastroPaciente;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Paciente")
@Table(name = "t_voll_paciente")
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }


    public void atualizaInformacoes(@Valid DadosAtualizaPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco = new Endereco(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
