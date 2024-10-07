package med.voll.api.medico.domain.dto;

import med.voll.api.medico.domain.entity.Medico;
import med.voll.api.medico.domain.enums.Especialidade;

public record DadosListagemMedico(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {

    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
