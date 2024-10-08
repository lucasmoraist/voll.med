package med.voll.api.medico.repository;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.medico.domain.entity.Medico;
import med.voll.api.medico.domain.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    @Query("""
            SELECT m FROM Medico m
            WHERE m.ativo = true
            AND m.especialidade = :especialidade
            AND m.id NOT IN (
                SELECT c.medico.id FROM Consulta c
                WHERE c.data = :data
            )
            ORDER BY RANDOM()
            LIMIT 1
            """)
    Medico escolherMedicoAleatorioLivreNadata(Especialidade especialidade, @NotNull(message = "Informe a data da consulta") @Future(message = "A data da consulta deve ser futura") LocalDateTime data);

    @Query("""
            SELECT m.ativo FROM Medico m
            WHERE m.id = :id
            """)
    Boolean findAtivoById(Long id);
}
