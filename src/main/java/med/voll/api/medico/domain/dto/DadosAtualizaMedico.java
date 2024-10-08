package med.voll.api.medico.domain.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.dto.DadosEndereco;

public record DadosAtualizaMedico(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
