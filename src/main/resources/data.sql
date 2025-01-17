-- Insert Egypt as a country (parent_id is NULL)
INSERT INTO address (address_id, name, parent_id)
SELECT 1, 'Egypt', NULL
WHERE NOT EXISTS (SELECT 1 FROM address WHERE name = 'Egypt');

-- Insert cities under Egypt (parent_id = 1)
INSERT INTO address (address_id, name, parent_id)
SELECT 2, 'Cairo', 1
WHERE NOT EXISTS (SELECT 1 FROM address WHERE name = 'Cairo' AND parent_id = 1);

INSERT INTO address (address_id, name, parent_id)
SELECT 3, 'Alexandria', 1
WHERE NOT EXISTS (SELECT 1 FROM address WHERE name = 'Alexandria' AND parent_id = 1);

INSERT INTO address (address_id, name, parent_id)
SELECT 4, 'Giza', 1
WHERE NOT EXISTS (SELECT 1 FROM address WHERE name = 'Giza' AND parent_id = 1);

INSERT INTO address (address_id, name, parent_id)
SELECT 5, 'Sharm El-Sheikh', 1
WHERE NOT EXISTS (SELECT 1 FROM address WHERE name = 'Sharm El-Sheikh' AND parent_id = 1);

INSERT INTO address (address_id, name, parent_id)
SELECT 6, 'Luxor', 1
WHERE NOT EXISTS (SELECT 1 FROM address WHERE name = 'Luxor' AND parent_id = 1);
