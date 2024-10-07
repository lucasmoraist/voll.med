package med.voll.api.medico.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.domain.dto.DadosEndereco;
import med.voll.api.medico.domain.enums.Especialidade;

public record DadosCadastroMedico(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O email é obrigatório") @Email(message = "O email é inválido")
        String email,

        @NotBlank(message = "O telefone é obrigatório")
        String telefone,

        @NotBlank(message = "O CRM é obrigatório") @Pattern(regexp = "\\d{4,6}", message = "O CRM é inválido")
        String crm,

        @NotNull(message = "A especialidade é obrigatória")
        Especialidade especialidade,

        @NotNull @Valid
        DadosEndereco endereco
) {}
