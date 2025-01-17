-- Create the address table if it doesn't exist
CREATE TABLE IF NOT EXISTS address (
    address_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parent_id INT,
    FOREIGN KEY (parent_id) REFERENCES address(address_id)
);

-- Insert Egypt as a country (parent_id is NULL)
INSERT INTO address (address_id, name, parent_id)
SELECT 1, 'Egypt', NULL
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM address WHERE name = 'Egypt');

-- Insert cities under Egypt (parent_id = 1)
INSERT INTO address (address_id, name, parent_id)
SELECT 2, 'Cairo', 1
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM address WHERE name = 'Cairo' AND parent_id = 1);

INSERT INTO address (address_id, name, parent_id)
SELECT 3, 'Alexandria', 1
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM address WHERE name = 'Alexandria' AND parent_id = 1);

INSERT INTO address (address_id, name, parent_id)
SELECT 4, 'Giza', 1
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM address WHERE name = 'Giza' AND parent_id = 1);

INSERT INTO address (address_id, name, parent_id)
SELECT 5, 'Sharm El-Sheikh', 1
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM address WHERE name = 'Sharm El-Sheikh' AND parent_id = 1);

INSERT INTO address (address_id, name, parent_id)
SELECT 6, 'Luxor', 1
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM address WHERE name = 'Luxor' AND parent_id = 1);

INSERT INTO address (address_id, name, parent_id)
SELECT 7, 'Banha', 1
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM address WHERE name = 'Banha' AND parent_id = 1);
