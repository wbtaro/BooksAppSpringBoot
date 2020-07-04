CREATE SEQUENCE IF NOT EXISTS book_id_seq;
CREATE TABLE IF NOT EXISTS book(
  id INTEGER DEFAULT nextval('book_id_seq') PRIMARY KEY,
  title VARCHAR(50),
  author VARCHAR(50),
  description VARCHAR(200)
);
