package med.voll.api.consultas.service;

import med.voll.api.consultas.domain.dto.DadosAgendamentoConsulta;
import med.voll.api.consultas.domain.dto.DadosDetalhamentoConsulta;

public interface ConsultaService {
    void agendarConsulta(DadosAgendamentoConsulta dados);
}
