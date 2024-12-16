-- Crear la tabla candidates
CREATE TABLE candidates (
    id BIGSERIAL PRIMARY KEY,                 -- Usamos BIGSERIAL para la auto-incrementación
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    gender VARCHAR(10),
    expected_salary DECIMAL(10, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear un trigger para actualizar el campo updated_at cuando se actualiza la fila
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;  -- Establece updated_at al momento actual
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Asignamos el trigger para actualizar updated_at cuando se haga un UPDATE en la tabla
CREATE TRIGGER update_candidates_updated_at
BEFORE UPDATE ON candidates
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

-- Insertar datos de prueba
INSERT INTO candidates (name, email, gender, expected_salary) VALUES
('Juan Pérez', 'juan.perez@example.com', 'Masculino', 50000.00),
('Ana Gómez', 'ana.gomez@example.com', 'Femenino', 60000.00),
('Miguel Torres', 'miguel.torres@example.com', 'Masculino', 55000.00),
('María López', 'maria.lopez@example.com', 'Femenino', 62000.00),
('Alex Cruz', 'alex.cruz@example.com', 'Otro', 58000.00);
