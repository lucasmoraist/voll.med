package med.voll.api.consultas.validations;

import med.voll.api.consultas.domain.dto.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsulta dados);

}
