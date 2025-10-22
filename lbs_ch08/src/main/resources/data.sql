DELETE  FROM article
INSERT INTO article (id, title, content, created_at, updated_at) VALUES (article_seq.NEXTVAL, '제목1', '안녕하세요.', sysdate, sysdate)
INSERT INTO article (id, title, content, created_at, updated_at) VALUES (article_seq.NEXTVAL, '제목2', '좋은 아침입니다.', sysdate, sysdate)
INSERT INTO article (id, title, content, created_at, updated_at) VALUES (article_seq.NEXTVAL, '제목3', '감사합니다!', sysdate, sysdate)