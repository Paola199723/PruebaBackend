INSERT INTO medicos (id, full_name, specialty, phone)
SELECT RANDOM_UUID(), 'Dra. María González', 'Cardiología', '555-1001'
WHERE NOT EXISTS (SELECT 1 FROM medicos WHERE full_name = 'Dra. María González');

INSERT INTO medicos (id, full_name, specialty, phone)
SELECT RANDOM_UUID(), 'Dr. Carlos Ruiz', 'Pediatría', '555-1002'
WHERE NOT EXISTS (SELECT 1 FROM medicos WHERE full_name = 'Dr. Carlos Ruiz');

INSERT INTO medicos (id, full_name, specialty, phone)
SELECT RANDOM_UUID(), 'Dra. Ana López', 'Dermatología', '555-1003'
WHERE NOT EXISTS (SELECT 1 FROM medicos WHERE full_name = 'Dra. Ana López');
