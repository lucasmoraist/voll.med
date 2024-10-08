package med.voll.api.consultas.domain.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.medico.domain.enums.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,

        @NotNull(message = "O id do paciente é obrigatório")
        Long idPaciente,

        @NotNull(message = "Informe a data da consulta") @Future(message = "A data da consulta deve ser futura")
        LocalDateTime data,
        Especialidade especialidade
) {
}
