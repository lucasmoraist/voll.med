CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE IF NOT EXISTS t_voll_consulta (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    medico_id BIGINT NOT NULL,
    paciente_id BIGINT NOT NULL,
    data DATE NOT NULL,

    FOREIGN KEY (medico_id) REFERENCES t_voll_medico(id),
    FOREIGN KEY (paciente_id) REFERENCES t_voll_paciente(id)
);