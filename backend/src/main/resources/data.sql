INSERT INTO tb_formulario (nota) VALUES (10);
INSERT INTO tb_formulario (nota) VALUES (9);
INSERT INTO tb_formulario (nota) VALUES (8);
INSERT INTO tb_formulario (nota) VALUES (7);
INSERT INTO tb_formulario (nota) VALUES (6);
INSERT INTO tb_formulario (nota) VALUES (5);
INSERT INTO tb_formulario (nota) VALUES (4);
INSERT INTO tb_formulario (nota) VALUES (3);
INSERT INTO tb_formulario (nota) VALUES (2);
INSERT INTO tb_formulario (nota) VALUES (1);


INSERT INTO tb_avaliacao (titulo, descricao, formulario_id) VALUES ('titulo 1', 'descricao 1', 2);
INSERT INTO tb_avaliacao (titulo, descricao, formulario_id) VALUES ('titulo 2', 'descricao 2', 5);
INSERT INTO tb_avaliacao (titulo, descricao, formulario_id) VALUES ('titulo 3', 'descricao 3', 4);
INSERT INTO tb_avaliacao (titulo, descricao, formulario_id) VALUES ('titulo 4', 'descricao 4', 1);



INSERT INTO tb_aluno (nome, email, idade) VALUES ('Joao', 'joao@email.com.br', 39);
INSERT INTO tb_aluno (nome, email, idade) VALUES ('Maria', 'maria@zup.com.br', 18);
INSERT INTO tb_aluno (nome, email, idade) VALUES ('Carol', 'carol@zup.com.br', 19);
INSERT INTO tb_aluno (nome, email, idade) VALUES ('Pedro', 'pedro@email.com.br', 48);



INSERT INTO tb_resposta (id_aluno, id_avaliacao) VALUES (1, 2);
INSERT INTO tb_resposta (id_aluno, id_avaliacao) VALUES (2, 4);
INSERT INTO tb_resposta (id_aluno, id_avaliacao) VALUES (3, 1);
INSERT INTO tb_resposta (id_aluno, id_avaliacao) VALUES (4, 3);


INSERT INTO user (nome, email, senha) VALUES ('Aluno', 'aluno@email.com', '$2a$10$bjSu8X6V1enJ8rQj2TIc/OlV8Q1GWm8Sbqo2.IbpltO9nJVdiENLK');
