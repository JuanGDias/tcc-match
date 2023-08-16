insert into role (ID, DESCRIPTION, NAME)
values(1, 'Cargo do admin', 'ADMIN');

insert into role (ID, DESCRIPTION, NAME)
values(2, 'Cargo do aluno', 'STUDENT');

insert into role (ID, DESCRIPTION, NAME)
values(3, 'Cargo do professor', 'TEACHER');

-- Senha: 123
insert into usuario (ID, USERNAME, PASSWORD)
values(10001, 'admin', '$2a$10$q3eNjhxHgnKItLchSFtGAOZuWzn0h006WqYwxoVoF/bV2q2mhM0Zy');

insert into user_roles (ID_USER, ID_ROLE)
values(10001, 1);

insert into coordenador (ID, NOME, EMAIL, USUARIO_ID)
values(1, 'Jurandi', 'jurandi@ccc.ufcg.cordenacao', 10001);

-- Senha: 456abc
insert into usuario (ID, USERNAME, PASSWORD)
values(10002, 'student', '$2a$10$OHc9pifrndBzuUdsIp6jQejMg2/VdBJmRWnTBOQ/eVNNtblsC6aFS');

insert into user_roles (ID_USER, ID_ROLE)
values(10002, 2);

insert into aluno(ID, EMAIL, MATRICULA, NOME, PERIODO_PREVISTO_PARA_CONCLUSAO, USUARIO_ID)
values(1, 'fernandinhoreidelas@ccc.ufcg.edu', '119210000', 'Fernando', '2021.2', 10002);

-- Senha: 789abc
insert into usuario (ID, USERNAME, PASSWORD)
values(10003, 'teacher', '$2a$10$q4KCTn27uCzldEuPM6Z1Vel1cHqCbEnDZSIMrDUdpC0SPCb31suE6');

insert into user_roles (ID_USER, ID_ROLE)
values(10003, 3);

insert into professor (ID, QUOTA_DE_DISPONIBILIDADE, EMAIL, NOME, USUARIO_ID)
values(1, 2, 'and@', 'anderson', 10003);

insert into area_estudo values(1, 'IA');
insert into area_estudo values(2, 'Machine learning');
insert into area_estudo values(3, 'DB');