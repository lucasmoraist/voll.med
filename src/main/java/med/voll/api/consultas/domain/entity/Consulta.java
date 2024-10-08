package med.voll.api.consultas.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.medico.domain.entity.Medico;
import med.voll.api.pacientes.domain.entity.Paciente;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Consulta")
@Table(name = "t_voll_consulta")
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;

}
