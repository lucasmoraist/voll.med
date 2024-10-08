package med.voll.api.consultas.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
        @JsonAlias({"id_medico", "medico_id"}) Long idMedico,

        @NotNull(message = "O id do paciente é obrigatório")
        @JsonAlias({"id_paciente", "paciente_id"}) Long idPaciente,

        @NotNull(message = "Informe a data da consulta")
        @Future(message = "A data da consulta deve ser futura")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data
) {
}
