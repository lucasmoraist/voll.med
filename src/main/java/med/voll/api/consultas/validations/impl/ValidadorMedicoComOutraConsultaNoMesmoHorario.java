package med.voll.api.consultas.validations.impl;

import med.voll.api.consultas.domain.dto.DadosAgendamentoConsulta;
import med.voll.api.consultas.repository.ConsultaRepository;
import med.voll.api.consultas.validations.ValidadorAgendamentoDeConsulta;
import med.voll.api.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var consulta = this.repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (consulta) {
            throw new ValidacaoException("Médico já possui consulta agendada para o horário informado");
        }
    }

}
