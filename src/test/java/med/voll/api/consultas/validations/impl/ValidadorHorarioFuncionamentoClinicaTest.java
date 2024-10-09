package med.voll.api.consultas.validations.impl;

import med.voll.api.consultas.domain.dto.DadosAgendamentoConsulta;
import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.medico.domain.enums.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static java.time.temporal.TemporalAdjusters.next;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ValidadorHorarioFuncionamentoClinicaTest {

    @Autowired
    private ValidadorHorarioFuncionamentoClinica validador;

    @Test
    @DisplayName("Deveria lançar exceção quando a consulta for agendada antes do horário de abertura")
    void case01 () {
        var horaAntesDaAbertura = LocalDateTime.of(2021, 1, 1, 6, 0);
        var dados = new DadosAgendamentoConsulta(1L, 1L, horaAntesDaAbertura, Especialidade.ORTOPEDIA);

        assertThrows(ValidacaoException.class, () -> this.validador.validar(dados));
    }

    @Test
    @DisplayName("Deveria lançar exceção quando a consulta for agendada depois do horário de fechamento")
    void case02 () {
        var horaDepoisDoFechamento = LocalDateTime.of(2021, 1, 1, 19, 0);
        var dados = new DadosAgendamentoConsulta(1L, 1L, horaDepoisDoFechamento, Especialidade.ORTOPEDIA);

        assertThrows(ValidacaoException.class, () -> this.validador.validar(dados));
    }

    @Test
    @DisplayName("Deveria lançar exceção quando a consulta for agendada em um domingo")
    void case03 () {
        var proximoDomingo = LocalDateTime.now()
                .with(next(DayOfWeek.SUNDAY))
                .withHour(10);

        var dados = new DadosAgendamentoConsulta(1L, 1L, proximoDomingo, Especialidade.ORTOPEDIA);

        assertThrows(ValidacaoException.class, () -> validador.validar(dados));

    }

}