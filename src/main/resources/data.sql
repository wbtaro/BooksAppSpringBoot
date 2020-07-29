/* パスワードは全員test */
DELETE FROM account;
INSERT INTO account VALUES 
  (1, 'a@a.a', '$2a$10$NU63igne1MpDvlNz4J5NFOP3JIjejA.FZaU6fvuCqvXnrGGoT2NT6', 'aaa', 'yoroshiku!!', 'USER', '2015-01-01', '2015-01-01'),
  (2, 'b@b.b', '$2a$10$NU63igne1MpDvlNz4J5NFOP3JIjejA.FZaU6fvuCqvXnrGGoT2NT6', 'bbb', 'konchiha!!', 'USER', '2016-02-02', '2016-02-02'),
  (3, 'c@c.c', '$2a$10$NU63igne1MpDvlNz4J5NFOP3JIjejA.FZaU6fvuCqvXnrGGoT2NT6', 'ccc', 'saiko!!', 'USER', '2016-05-05', '2016-06-06'),
  (4, 'd@d.d', '$2a$10$NU63igne1MpDvlNz4J5NFOP3JIjejA.FZaU6fvuCqvXnrGGoT2NT6', 'ddd', 'yoroshiku!!', 'USER', '2015-01-01', '2015-01-01'),
  (5, 'e@e.e', '$2a$10$NU63igne1MpDvlNz4J5NFOP3JIjejA.FZaU6fvuCqvXnrGGoT2NT6', 'eee', 'konchiha!!', 'USER', '2016-02-02', '2016-02-02'),
  (6, 'f@f.f', '$2a$10$NU63igne1MpDvlNz4J5NFOP3JIjejA.FZaU6fvuCqvXnrGGoT2NT6', 'fff', 'saiko!!', 'USER', '2016-05-05', '2016-06-06');

DELETE FROM book;
INSERT INTO book VALUES 
  (1, 'harry', 'tanaka', 'omoroi!!', '2000-01-01', '2000-01-01'),
  (2, 'ok', 'tanaka', 'saiko!!', '2001-02-02', '2001-02-02'),
  (3, 'kaimono', 'ishida', 'imaichi!!', '2002-05-05', '2002-06-06'),
  (4, 'potter', 'koyama', 'sugee', '2003-01-01', '2003-01-01'),
  (5, 'pokemon', 'satoshi', 'get!!', '2004-07-07', '2004-07-07'),
  (6, 'igo', 'iyama', 'muzukashi!!', '2005-05-05', '2005-06-06');
