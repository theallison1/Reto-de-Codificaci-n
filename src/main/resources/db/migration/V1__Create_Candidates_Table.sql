CREATE TABLE candidates (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    gender VARCHAR(10),
    expected_salary DECIMAL(10, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insertar datos de prueba
INSERT INTO candidates (name, email, gender, expected_salary) VALUES
('Juan Pérez', 'juan.perez@example.com', 'Masculino', 50000.00),
('Ana Gómez', 'ana.gomez@example.com', 'Femenino', 60000.00),
('Miguel Torres', 'miguel.torres@example.com', 'Masculino', 55000.00),
('María López', 'maria.lopez@example.com', 'Femenino', 62000.00),
('Alex Cruz', 'alex.cruz@example.com', 'Otro', 58000.00);
