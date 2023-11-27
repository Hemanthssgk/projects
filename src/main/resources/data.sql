INSERT INTO role (id, role_name) VALUES (501, 'ROLE_ADMIN')
ON DUPLICATE KEY UPDATE 
    id = VALUES(id),
    role_name = VALUES(role_name);

INSERT INTO role (id, role_name) VALUES (502, 'ROLE_USER')
ON DUPLICATE KEY UPDATE 
    id = VALUES(id),
    role_name = VALUES(role_name);
