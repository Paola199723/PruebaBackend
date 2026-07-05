-- Esquema para la tabla `medicos` acorde al modelo de dominio
-- Nota: la generación del UUID se realiza desde la aplicación (Hibernate),
-- por lo que aquí sólo definimos la columna `id` como UUID.
CREATE TABLE IF NOT EXISTS medicos (
  id UUID PRIMARY KEY,
  full_name VARCHAR(100) NOT NULL,
  specialty VARCHAR(100) NOT NULL,
  phone VARCHAR(20),
  email VARCHAR(100),
  CONSTRAINT chk_full_name_length CHECK (char_length(full_name) BETWEEN 3 AND 100),
  CONSTRAINT chk_phone_length CHECK (phone IS NULL OR char_length(phone) >= 7)
);
