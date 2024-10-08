package med.voll.api.endereco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(

        @NotBlank(message = "O logradouro é obrigatório")
        String logradouro,

        @NotBlank(message = "O bairro é obrigatório")
        String bairro,

        @NotBlank(message = "O CEP é obrigatório") @Pattern(regexp = "\\d{8}", message = "O CEP é inválido")
        String cep,

        @NotBlank(message = "A cidade é obrigatória")
        String cidade,

        @NotBlank(message = "O estado é obrigatório")
        String uf,

        String complemento,
        String numero
) {
}
