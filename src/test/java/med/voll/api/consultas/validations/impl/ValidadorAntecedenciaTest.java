package med.voll.api.consultas.validations.impl;

import med.voll.api.consultas.domain.dto.DadosAgendamentoConsulta;
import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.medico.domain.enums.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ValidadorAntecedenciaTest {

    @Autowired
    private ValidadorAntecedencia validador;

    @Test
    @DisplayName("Deveria lançar exceção quando a consulta for agendada com menos de 30 minutos de antecedência")
    void case01() {
        var agora = LocalDateTime.now();
        var data = agora.plusMinutes(29);
        var dados = new DadosAgendamentoConsulta(1L, 1L, data, Especialidade.ORTOPEDIA);

        assertThrows(ValidacaoException.class, () -> this.validador.validar(dados));
    }

}