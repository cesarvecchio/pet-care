INSERT INTO TB_USUARIO (NOME, EMAIL, SENHA, CEP, LOGRADOURO, COMPLEMENTO, BAIRRO, CIDADE, UF, NUMERO, LATITUDE,
                        LONGITUDE)
VALUES ('Grupo', 'grupo@gmail.com', 'grupo', '01310930', 'Av Paulista', 'Ao Lado do Banco Itaú', 'Bela Vista',
        'São Paulo', 'SP', '1000', -23.562372,
        -46.653939);

INSERT INTO TB_DONO (ID, CPF, RG)
VALUES (1, '12313131313', '23323322');

INSERT INTO tb_pet (nome, especie, raca, humor, genero, dono_id)
VALUES ('Pet do grupo', 1, 4, 1, 2, 1);

INSERT INTO TB_USUARIO (NOME, EMAIL, SENHA, CEP, LOGRADOURO, COMPLEMENTO, BAIRRO, CIDADE, UF, NUMERO, LATITUDE,
                        LONGITUDE)
VALUES ('PET LOVE', 'admin@petlove.com', 'petlovepasswd', '02525050', 'Avenida Braz Leme', 'Lojas 16 e 17', 'Santana', 'São Paulo', 'SP', '1200',
        -23.507549, -46.648546);

INSERT INTO TB_PET_SHOP (ID, CNPJ, TIPO_SERVICO)
VALUES (2, '10864846000123', 1);

INSERT INTO TB_FUNCIONARIO (NOME, CPF, RG, PET_SHOP_ID)
VALUES ('Kleber Machado', '12378919426', '768945803', 2);

