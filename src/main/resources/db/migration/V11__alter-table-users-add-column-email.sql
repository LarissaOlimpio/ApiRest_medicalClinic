ALTER TABLE users ADD COLUMN email VARCHAR(100);
UPDATE users SET email = 'email not registered' WHERE email IS NULL;
ALTER TABLE users ADD CONSTRAINT unique_email UNIQUE (email);