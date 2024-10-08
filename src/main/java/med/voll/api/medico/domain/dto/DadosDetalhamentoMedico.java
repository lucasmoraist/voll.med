package med.voll.api.medico.domain.dto;

import med.voll.api.endereco.entity.Endereco;
import med.voll.api.medico.domain.entity.Medico;
import med.voll.api.medico.domain.enums.Especialidade;

public record DadosDetalhamentoMedico(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
) {
    public DadosDetalhamentoMedico(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getTelefone(),
                medico.getEspecialidade(),
                medico.getEndereco());
    }
}
