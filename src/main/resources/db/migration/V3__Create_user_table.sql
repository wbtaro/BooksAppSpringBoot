CREATE SEQUENCE IF NOT EXISTS account_id_seq;
CREATE TABLE IF NOT EXISTS account(
  id INTEGER DEFAULT nextval('account_id_seq') PRIMARY KEY,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(500) NOT NULL,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(200),
  role VARCHAR(50) DEFAULT 'USER',
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);
