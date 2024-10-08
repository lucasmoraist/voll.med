package med.voll.api.consultas.validations.impl;

import med.voll.api.consultas.domain.dto.DadosAgendamentoConsulta;
import med.voll.api.consultas.validations.ValidadorAgendamentoDeConsulta;
import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = this.repository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Médico não está ativo");
        }
    }

}
