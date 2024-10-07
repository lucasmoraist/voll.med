package med.voll.api.pacientes.domain.dto;

import med.voll.api.endereco.domain.dto.DadosEndereco;

public record DadosAtualizaPaciente(
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
