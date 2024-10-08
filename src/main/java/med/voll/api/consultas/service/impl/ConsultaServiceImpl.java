package med.voll.api.consultas.service.impl;

import lombok.RequiredArgsConstructor;
import med.voll.api.consultas.domain.dto.DadosAgendamentoConsulta;
import med.voll.api.consultas.domain.entity.Consulta;
import med.voll.api.consultas.repository.ConsultaRepository;
import med.voll.api.consultas.service.ConsultaService;
import med.voll.api.consultas.validations.ValidadorAgendamentoDeConsulta;
import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.medico.domain.entity.Medico;
import med.voll.api.medico.repository.MedicoRepository;
import med.voll.api.pacientes.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final List<ValidadorAgendamentoDeConsulta> validadores;

    @Override
    public void agendarConsulta(DadosAgendamentoConsulta dados) {

        this.validadores.forEach(v -> v.validar(dados));

        var medico = this.escolherMedico(dados);

        var paciente = this.pacienteRepository.findById(dados.idPaciente())
                .orElseThrow(() -> new ValidacaoException("Paciente não encontrado"));

        var consulta = Consulta.builder()
                .medico(medico)
                .paciente(paciente)
                .data(dados.data())
                .build();

        this.consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return this.medicoRepository.findById(dados.idMedico())
                    .orElseThrow(() -> new ValidacaoException("Médico não encontrado"));
        }
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória");
        }
        return this.medicoRepository.escolherMedicoAleatorioLivreNadata(dados.especialidade(), dados.data());
    }

}
