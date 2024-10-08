package med.voll.api.pacientes.repository;

import jakarta.validation.constraints.NotNull;
import med.voll.api.pacientes.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("""
            SELECT p.ativo FROM Paciente p
            WHERE p.id = :id
            """)
    boolean findAtivoById(Long id);
}
